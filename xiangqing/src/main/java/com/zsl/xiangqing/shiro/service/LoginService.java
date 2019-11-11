package com.zsl.xiangqing.shiro.service;

import com.zsl.xiangqing.common.enums.UserStatus;
import com.zsl.xiangqing.constant.Constants;
import com.zsl.xiangqing.constant.ShiroConstants;
import com.zsl.xiangqing.constant.UserConstants;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.exception.user.*;
import com.zsl.xiangqing.manager.AsyncManager;
import com.zsl.xiangqing.manager.factory.AsyncFactory;
import com.zsl.xiangqing.service.IUserService;
import com.zsl.xiangqing.shiro.ShiroUtils;
import com.zsl.xiangqing.util.DateUtils;
import com.zsl.xiangqing.util.MessageUtils;
import com.zsl.xiangqing.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 */
@Component
public class LoginService {

    /**
     * 验证密码
     */
    private PasswordService passwordService;

    /**
     * 验证账号
     */
    private IUserService userService;

    @Autowired
    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     */
    public Users login(String username, String password) {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.username.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        Users user = userService.selectUserByLoginName(username);

        //可能是用的手机号登录的
        if (user == null && maybeMobilePhoneNumber(username)) {
            user = userService.selectUserByPhoneNumber(username);
        }
        //可能是用的电子邮件登录的
        if (user == null && maybeEmail(username)) {
            user = userService.selectUserByEmail(username);
        }
        //经过上面的筛选依然为空，说明该用户不存在
        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        //查找到用户，还要继续检测用户的状态,如果已经被删除，封号等
        if (UserStatus.DELETED.getCode() == user.getDelFlag()) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }
        //是否已经停用
        if (UserStatus.DISABLE.getCode() == user.getStatus()) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.blocked")));
            throw new UserBlockedException();
        }

        //上面是验证的账号，下面验证账号和密码
        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLoginInformation(username, Constants.LOGIN_SUCCESS,
                "登录成功"));
        //认证成功登录进来，更新用户某些字段
        recordLoginInfo(user);
        return user;
    }

    /**
     * 是否为邮件作为账号
     * @param username
     * @return
     */
    private boolean maybeEmail(String username) {
        return username.matches(UserConstants.EMAIL_PATTERN);
    }

    /**
     * 是否用的手机号登录的
     * @param username
     * @return
     */
    private boolean maybeMobilePhoneNumber(String username) {
        return username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN);
    }

    /**
     * 记录登录信息
     */
    private void recordLoginInfo(Users user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setUpdate_time(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
