package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.*;
import com.zsl.boss.demo.service.UserService;
import com.zsl.boss.demo.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//参考博文，Swagger注解详解 https://blog.csdn.net/u014231523/article/details/76522486
@Api(value = "更新应聘用户基本信息接口", tags = "更新应聘用户基本信息接口")
@RestController
public class UserBaseMsgController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "更新参加工作时间 年-月", httpMethod = "post")
    @PostMapping("/updateJoinWorkTime")
    public ResponseEntity<JsonResult> updateJoinWorkTime(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                         @ApiParam(name = "joinWorkTime", value = "参加工作时间", required = true) String joinWorkTime) {
        JsonResult jsonResult = new JsonResult();
        //是否更新成功
        if (userService.updateJoinWorkTime(userId, joinWorkTime)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新出生年月", httpMethod = "post")
    @PostMapping("/updateDateOfBirth")
    public ResponseEntity<JsonResult> updateDateOfBirth(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                        @ApiParam(name = "dateOfBirth", value = "出生年月", required = true) String dateOfBirth) {
        JsonResult jsonResult = new JsonResult();
        if (userService.updateDateOfBirth(userId, dateOfBirth)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新我的优势", httpMethod = "post")
    @PostMapping("/updateSuperiority")
    public ResponseEntity<JsonResult> updateSuperiority(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                        @ApiParam(name = "superiority", value = "我的优势", required = true) String superiority) {
        JsonResult jsonResult = new JsonResult();
        if (userService.updateSuperiority(userId, superiority)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新求职状态", httpMethod = "post")
    @PostMapping("/updateJobStatus")
    public ResponseEntity<JsonResult> updateJobStatus(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                      @ApiParam(name = "jobStatus", value = "求职状态", required = true) Integer jobStatus) {
        JsonResult jsonResult = new JsonResult();
        if (userService.updateJobStatus(userId, jobStatus)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询求职状态", httpMethod = "post")
    @PostMapping("/selectJobStatus")
    public ResponseEntity<JsonResult> selectJobStatus(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(Status.SUCCESS);
        jsonResult.setResult(userService.selectJobStatus(userId));
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询个人信息", httpMethod = "post")
    @PostMapping("/selectUser")
    public ResponseEntity<JsonResult> selectUser(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId) {
        JsonResult jsonResult = new JsonResult();
        Users user = userService.selectUserById(userId);
        jsonResult.setStatus(Status.SUCCESS);
        jsonResult.setResult(user);
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加求职期望", httpMethod = "post")
    @PostMapping("/addJobExpectation")
    public ResponseEntity<JsonResult> addJobExpectation(@ApiParam(name = "json", value = "求职期望对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        JobExpectation jobExpectation = JsonUtils.jsonToPojo(json, JobExpectation.class);
        if (userService.addJobExpectation(jobExpectation)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询求职期望", httpMethod = "post")
    @PostMapping("/selectJobExpectation")
    public ResponseEntity<JsonResult> selectJobExpectation(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                           @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<JobExpectation> jobExpectationList = userService.selectJobExpectation(userId, page);
        if (jobExpectationList != null && jobExpectationList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(jobExpectationList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加工作经历", httpMethod = "post")
    @PostMapping("/addWorkExperience")
    public ResponseEntity<JsonResult> addWorkExperience(@ApiParam(name = "json", value = "工作经历对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        WorkExperience workExperience = JsonUtils.jsonToPojo(json, WorkExperience.class);
        if (userService.addWorkExperience(workExperience)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询工作经历", httpMethod = "post")
    @PostMapping("/selectWorkExperience")
    public ResponseEntity<JsonResult> selectWorkExperience(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                           @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<WorkExperience> workExperienceList = userService.selectWorkExperience(userId, page);
        if (workExperienceList != null && workExperienceList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(workExperienceList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加项目经历", httpMethod = "post")
    @PostMapping("/addProjectExpectation")
    public ResponseEntity<JsonResult> addProjectExpectation(@ApiParam(name = "json", value = "项目经历对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        ProjectExpectation projectExpectation = JsonUtils.jsonToPojo(json, ProjectExpectation.class);
        if (userService.addProjectExpectation(projectExpectation)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询项目经历", httpMethod = "post")
    @PostMapping("/selectProjectExpectation")
    public ResponseEntity<JsonResult> selectProjectExpectation(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                               @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<ProjectExpectation> projectExpectationList = userService.selectProjectExpectation(userId, page);
        if (projectExpectationList != null && projectExpectationList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(projectExpectationList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加教育经历", httpMethod = "post")
    @PostMapping("/addEducationExpectation")
    public ResponseEntity<JsonResult> addEducationExpectation(@ApiParam(name = "json", value = "教育经历对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        EducationExpectation educationExpectation = JsonUtils.jsonToPojo(json, EducationExpectation.class);
        if (userService.addEducationExpectation(educationExpectation)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询教育经历", httpMethod = "post")
    @PostMapping("/selectEducationExpectation")
    public ResponseEntity<JsonResult> selectEducationExpectation(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                                 @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<EducationExpectation> educationExpectationList = userService.selectEducationExpectation(userId, page);
        if (educationExpectationList != null && educationExpectationList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(educationExpectationList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加社交主页", httpMethod = "post")
    @PostMapping("/addSocialHomePage")
    public ResponseEntity<JsonResult> addSocialHomePage(@ApiParam(name = "json", value = "社交主页对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        SocialHomePage socialHomePage = JsonUtils.jsonToPojo(json, SocialHomePage.class);
        if (userService.addSocialHomePage(socialHomePage)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询社交主页", httpMethod = "post")
    @PostMapping("/selectSocialHomePage")
    public ResponseEntity<JsonResult> selectSocialHomePage(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                           @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<SocialHomePage> socialHomePageList = userService.selectSocialHomePage(userId, page);
        if (socialHomePageList != null && socialHomePageList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(socialHomePageList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加志愿服务经历", httpMethod = "post")
    @PostMapping("/addVoluntary")
    public ResponseEntity<JsonResult> addVoluntary(@ApiParam(name = "voluntary", value = "志愿服务经历对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        Voluntary voluntary = JsonUtils.jsonToPojo(json, Voluntary.class);
        if (userService.addVoluntary(voluntary)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询志愿服务经历", httpMethod = "post")
    @PostMapping("/selectVoluntary")
    public ResponseEntity<JsonResult> selectVoluntary(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                      @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<Voluntary> voluntaryList = userService.selectVoluntary(userId, page);
        if (voluntaryList != null && voluntaryList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(voluntaryList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

}
