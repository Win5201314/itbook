package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.Interview;
import com.zsl.boss.demo.service.RecruitersService;
import com.zsl.boss.demo.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//参考博文，Swagger注解详解 https://blog.csdn.net/u014231523/article/details/76522486
@Api(value = "更新招聘用户基本信息接口", tags = "更新招聘用户基本信息接口")
@RestController
public class RecruitersBaseMsgController {

    @Autowired
    RecruitersService recruitersService;

    @ApiOperation(value = "接收简历邮箱", httpMethod = "post")
    @PostMapping("/updateEmail")
    public ResponseEntity<JsonResult> updateEmail(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                  @ApiParam(name = "email", value = "邮件", required = true) String email) {
        JsonResult jsonResult = new JsonResult();
        if (recruitersService.updateEmail(userId, email)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新职务", httpMethod = "post")
    @PostMapping("/updateDuty")
    public ResponseEntity<JsonResult> updateDuty(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                 @ApiParam(name = "duty", value = "职务", required = true) String duty) {
        JsonResult jsonResult = new JsonResult();
        if (recruitersService.updateDuty(userId, duty)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新团队介绍", httpMethod = "post")
    @PostMapping("/updateTeamIntroduction")
    public ResponseEntity<JsonResult> updateTeamIntroduction(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                             @ApiParam(name = "teamIntroduction", value = "团队介绍", required = true) String teamIntroduction) {
        JsonResult jsonResult = new JsonResult();
        if (recruitersService.updateTeamIntroduction(userId, teamIntroduction)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新团队亮点", httpMethod = "post")
    @PostMapping("/updateTeamLightspot")
    public ResponseEntity<JsonResult> updateTeamLightspot(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                          @ApiParam(name = "teamLightspot", value = "团队亮点", required = true) String teamLightspot) {
        JsonResult jsonResult = new JsonResult();
        if (recruitersService.updateTeamLightspot(userId, teamLightspot)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "创建面试", httpMethod = "post")
    @PostMapping("/addInterview")
    public ResponseEntity<JsonResult> addInterview(@ApiParam(name = "json", value = "面试对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        Interview interview = JsonUtils.jsonToPojo(json, Interview.class);
        if (recruitersService.addInterview(interview)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }
}
