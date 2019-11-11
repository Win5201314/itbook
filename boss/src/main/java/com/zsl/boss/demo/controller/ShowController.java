package com.zsl.boss.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "展示各种页面的控制器")
@Controller
public class ShowController {

    /**
     * 登出
     * @return 返回对应的页面
     */
    @ApiOperation(value = "返回登出页面")
    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

}
