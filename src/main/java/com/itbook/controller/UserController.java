package com.itbook.controller;

import com.itbook.common.HttpResult;
import com.itbook.entity.User;
import com.itbook.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        User user = userService.findUserByloginNameAndpassword(loginName, password);
        if (user != null) {
            //登录成功
            return HttpResult.build(HttpResult.SUCCESS, null);
        } else {
            //登录失败
            return HttpResult.build(HttpResult.FAIL, null);
        }
    }

    /**
     * 注册账号
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult register(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        if (!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(password)) {
            User user = userService.findUserByloginNameAndpassword(loginName, password);
            if (user != null) {
                //已经注册过了
                return HttpResult.build(HttpResult.FAIL, "已经注册过了，请不要重复注册!");
            } else {
                //没有注册过, 加密放入数据库中
                userService.insertNewUser(loginName, DigestUtils.md5DigestAsHex(password.getBytes()));
                //注册成功
                return HttpResult.build(HttpResult.SUCCESS, "注册成功!");
            }
        }
        return HttpResult.build(HttpResult.FAIL, "账号密码信息不全!");
    }

}
