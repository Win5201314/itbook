package com.zsl.xiangqing.controller;

import com.zsl.xiangqing.common.ServerResponse;
import com.zsl.xiangqing.common.enums.ResponseCode;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户修改个人信息")
@RestController
public class UserController {

    private IUserService userService;
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "更改用户信息")
    @PostMapping("/updateUserInfo")
    public ServerResponse<String> updateUserInfo(@ApiParam(name = "users", value = "用户对象", required = true) Users users,
                                                 BindingResult bindingResult) throws Exception {
        // 1、JSR303,数据对象绑定出错
        if (bindingResult.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //更新用户信息
        if (userService.updateUserInfo(users)) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @ApiOperation(value = "未登录")
    @RequestMapping("/unauthorized")
    public ServerResponse<String> unauthorized() {
        return ServerResponse.createBySuccessMessage("未登录--------");
    }
}
