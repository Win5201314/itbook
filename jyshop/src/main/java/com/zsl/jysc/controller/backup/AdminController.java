package com.zsl.jysc.controller.backup;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.service.IAdminService;
import com.zsl.jysc.util.JWTUtil;
import com.zsl.jysc.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "管理员模块")
@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 注册不要，管理员比较少，数据直接修改数据库
     */
    @ApiOperation(value = "注册+登录一起")
    @PostMapping("/admin/justLogin")
    public ServerResponse<String> justLogin(@ApiParam(name = "username", value = "微信昵称", required = true) String username,
                                            @ApiParam(name = "password", value = "微信注册openid", required = true) String password) throws Exception {
        password = MD5Utils.MD5Encode(password, "UTF-8");
        if (adminService.isExitsAdmin(username, password)) {
            String token = JWTUtil.createAdminToken(username, password);
            return ServerResponse.createBySuccess("登陆成功", token);
        } else {
            return ServerResponse.createByErrorMessage("登陆失败");
        }
    }
}
