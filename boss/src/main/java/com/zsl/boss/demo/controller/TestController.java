package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试控制类", tags = "测试接口类")
@RestController
public class TestController {

    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    @GetMapping("/index")
    public ResponseEntity<JsonResult> index() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus("ok");
        jsonResult.setResult("hello!");
        return ResponseEntity.ok(jsonResult);
    }

}
