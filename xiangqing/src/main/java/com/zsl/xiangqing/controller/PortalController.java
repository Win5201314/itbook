package com.zsl.xiangqing.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zsl.xiangqing.common.ServerResponse;
import com.zsl.xiangqing.common.core.controller.BaseController;
import com.zsl.xiangqing.properties.UserProperties;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.service.IUserService;
import com.zsl.xiangqing.util.RedisUtil;
import com.zsl.xiangqing.util.StringUtils;
import com.zsl.xiangqing.util.aliyun.AliyunSmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用户入口模块，包含注册，登录，忘记密码，退出登录
 */
@Api(value = "用户入口模块，包含注册，登录，忘记密码，退出登录")
@RestController
@RequestMapping("/user")
public class PortalController extends BaseController {

    @Autowired
    private UserProperties userProperties;
    private RedisUtil redisUtil;
    private IUserService userService;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 注册采用填写账号 + 密码 + 短信验证码验证模式,账号就是手机号
     */
    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ServerResponse<String> register(@ApiParam(name = "username", value = "账号", required = true) String username,
                                   @ApiParam(name = "password", value = "密码", required = true) String password,
                                   @ApiParam(name = "code", value = "验证码", required = true) String code,
                                   @ApiParam(name = "openid", value = "微信注册openid", required = true) String openid) throws Exception {
        //首先查看当前账号手机号是否已经存在
        if (userService.isHavePhoneNumber(username)) {
            return ServerResponse.createByErrorMessage("该手机号已经注册了");
        }
        //从redis缓存中获取手机号对应的验证码
        String authCode = (String) redisUtil.get(username);
        //验证 验证码
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(authCode) && code.equals(authCode)) {
            //插入新用户到数据库
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(password);
            users.setOpenid(openid);
            if (userService.addNewUsers(users)) {
                return ServerResponse.createBySuccessMessage("注册成功");
            } else {
                //插入数据库失败
                return ServerResponse.createByErrorMessage("注册失败");
            }
        } else {
            return ServerResponse.createByErrorMessage("验证码过期，或者不对");
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ServerResponse<String> login(@ApiParam(name = "username", value = "账号", required = true) String username,
                                        @ApiParam(name = "password", value = "密码", required = true) String password,
                                        @ApiParam(name = "rememberMe", value = "是否记住", required = true) Boolean rememberMe) throws Exception {
        try {
            // 包装登录信息
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);
            // 获取验证主体
            Subject subject = SecurityUtils.getSubject();
            // 调用登录
            subject.login(usernamePasswordToken);
            // 没有报错则说明登录成功,否则将处理相应的异常
            // 在session中存放用户信息
            Users user = userService.selectUserByLoginName(username);
            subject.getSession().setAttribute("user", user);
            //登录成功则返回sessionId作为token给前端存储，前端请求时将该token放入请求头，以Authorization为key，以此来鉴权
            String token = subject.getSession().getId().toString();
            return ServerResponse.createBySuccess(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("账号或者密码不对");
        }
    }

    @ApiOperation(value = "退出系统")
    @PostMapping("/logout")
    public ServerResponse<String> logout() {
        try {
            // 主体退出系统
            SecurityUtils.getSubject().logout();
            return ServerResponse.createBySuccessMessage("退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            // 报错则说明退出失败
            return ServerResponse.createByErrorMessage("退出成功");
        }
    }

    /**
     * 分两种情况
     * 1.分为没登录进去时候忘记密码（需要短信验证）
     * 2.登录进去更改密码(不需要短信验证)
     */
    @ApiOperation(value = "更改密码")
    @PostMapping("/changePassword")
    public ServerResponse<String> changePassword(@ApiParam(name = "username", value = "账号", required = true) String username,
                                         @ApiParam(name = "password", value = "密码", required = true) String password,
                                         @ApiParam(name = "code", value = "验证码", required = true) String code) throws Exception {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            //分为没登录进去时候忘记密码（需要短信验证）
            if (StringUtils.isNotBlank(code)) {
                //从redis缓存中获取手机号对应的验证码
                String authCode = (String) redisUtil.get(username);
                //验证 验证码
                if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(authCode) && code.equals(authCode)) {
                    //更新数据库账号密码
                    if (userService.updatePasswordByUsername(username, password)) {
                        return ServerResponse.createBySuccessMessage("更改密码成功");
                    } else {
                        return ServerResponse.createByErrorMessage("更改密码失败");
                    }
                } else {
                    return ServerResponse.createByErrorMessage("验证码过期，或者不对");
                }
            } else {
                //登录进去更改密码(不需要短信验证)
                if (userService.updatePasswordByUsername(username, password)) {
                    return ServerResponse.createBySuccessMessage("更改密码成功");
                } else {
                    return ServerResponse.createByErrorMessage("更改密码失败");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数为空");
        }
    }

    /**
     * 微信登录
     * 如果数据库中有，则直接进去，否则，需要绑定手机号
     * @param openid 微信登录唯一标识
     * @return
     */
    @ApiOperation(value = "微信登录")
    @PostMapping("/wxLogin")
    public ServerResponse<String> wxLogin(@ApiParam(name = "openid", value = "微信唯一标识openid", required = true) String openid) throws Exception {
        if (userService.isHaveOpenid(openid)) {
            try {
                //根据openid获取当前对象
                Users user = userService.selectUserByOpenid(openid);
                // 包装登录信息
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                // 获取验证主体
                Subject subject = SecurityUtils.getSubject();
                // 调用登录
                subject.login(usernamePasswordToken);
                // 没有报错则说明登录成功,否则将处理相应的异常
                // 在session中存放用户信息
                subject.getSession().setAttribute("user", user);
                //登录成功则返回sessionId作为token给前端存储，前端请求时将该token放入请求头，以Authorization为key，以此来鉴权
                String token = subject.getSession().getId().toString();
                return ServerResponse.createBySuccess(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return ServerResponse.createByErrorMessage("账号或者密码不对");
            }
        } else {
            //没有则去注册绑定手机号,调用注册接口
            return ServerResponse.createByErrorMessage("去注册绑定手机号");
        }
    }

    /**
     * 发送短信验证码，用于注册登录和忘记密码
     * @param phoneNumber 手机号
     * @return 返回是否发送成功
     */
    @ApiOperation(value = "发送短信验证码，用于注册和忘记密码")
    @PostMapping("/sendSMS")
    public ServerResponse<String> sendSMS(@ApiParam(name = "phoneNumber", value = "手机号码", required = true) String phoneNumber) throws Exception {
        AliyunSmsUtils.setNewcode();
        String code = Integer.toString(AliyunSmsUtils.getNewcode());
        logger.info("发送的验证码为：" + code);
        //发短信
        SendSmsResponse response = null;
        try {
            response = AliyunSmsUtils.sendSms(phoneNumber, code);
            logger.info("短信接口返回的数据----------------");
            logger.info("Code=" + response.getCode());
            logger.info("Message=" + response.getMessage());
            logger.info("RequestId=" + response.getRequestId());
            logger.info("BizId=" + response.getBizId());
            if (response.getCode() != null && response.getCode().equals("OK")) {
                //将手机号和验证码缓存到redis中，时间按照设定的5分钟有效期
                redisUtil.set(phoneNumber, code, Long.parseLong(userProperties.getCodeExpireTime()));
                return ServerResponse.createBySuccessMessage("发送验证码成功");
            } else {
                return ServerResponse.createByErrorMessage("阿里云获取验证码失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("服务器访问阿里云出错");
        }
    }

    /**
     * 发送短信验证码，用于注册登录和忘记密码
     * @param phoneNumber 手机号
     * @return 返回是否发送成功
     */
    @ApiOperation(value = "发送短信验证码，用于注册和忘记密码")
    @PostMapping("/sendSMSf")
    public ServerResponse<String> sendSMS2(@ApiParam(name = "phoneNumber", value = "手机号码", required = true) String phoneNumber) throws Exception {
        //将手机号和验证码缓存到redis中，时间按照设定的5分钟有效期
        String res = UUID.randomUUID().toString();
        res = res.substring(0, 5);
        logger.debug("----------------------->" + res);
        redisUtil.set(phoneNumber, res, Long.parseLong(userProperties.getCodeExpireTime()));
        return ServerResponse.createBySuccessMessage("发送验证码成功");
    }

}
