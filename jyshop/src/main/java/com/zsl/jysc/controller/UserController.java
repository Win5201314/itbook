package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.entity.Users;
import com.zsl.jysc.service.IUserService;
import com.zsl.jysc.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "登录注册")
@RestController
@CrossOrigin //解决跨域请求问题
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "注册+登录一起")
    @PostMapping("/registerOrLogin")
    public ServerResponse<String> registerOrLogin(@ApiParam(name = "username", value = "微信昵称", required = true) String username,
                                           @ApiParam(name = "openid", value = "微信注册openid", required = true) String openid,
                                           @ApiParam(name = "avatar", value = "头像图片地址", required = true) String avatar) throws Exception {
        //说明已经注册过了，这里就相当于登录
        if (userService.isExistOpenid(openid)) {
            if (userService.updateUsernameAndAvatar(openid, username, avatar)) {
                long id = userService.selectIdByOpenid(openid);
                String token = JWTUtil.createToken(id, openid);
                return ServerResponse.createBySuccess("登录成功", token);
            } else {
                return ServerResponse.createByErrorMessage("登录失败，更新用户信息失败");
            }
        } else {
            //没有注册，这里就是注册
            Users users = new Users();
            users.setOpenid(openid);
            users.setUsername(username);
            users.setAvatar(avatar);
            if (userService.addNewUsers(users)) {
                long id = userService.selectIdByOpenid(openid);
                String token = JWTUtil.createToken(id, openid);
                return ServerResponse.createBySuccess("注册成功", token);
            } else {
                return ServerResponse.createByErrorMessage("注册失败，添加新账户失败");
            }
        }
    }
}
