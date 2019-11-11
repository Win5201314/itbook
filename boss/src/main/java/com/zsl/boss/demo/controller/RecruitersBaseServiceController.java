package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.CollectResume;
import com.zsl.boss.demo.entity.Job;
import com.zsl.boss.demo.entity.Prop;
import com.zsl.boss.demo.entity.Resume;
import com.zsl.boss.demo.service.RecruitersService;
import com.zsl.boss.demo.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "招聘用户一些基本服务的处理接口", tags = "招聘用户一些基本服务的处理接口")
@RestController
public class RecruitersBaseServiceController {

    private Logger logger = LoggerFactory.getLogger(RecruitersBaseServiceController.class);

    @Autowired
    RecruitersService recruitersService;

    @ApiOperation(value = "我的道具", httpMethod = "post")
    @PostMapping(value = "/myProp")
    public ResponseEntity<JsonResult> selectMyProp(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                   @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<Prop> props = recruitersService.selectMyPropByRecruterId(userId, page);
        if (props != null && props.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(props);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "发布职位", httpMethod = "post")
    @PostMapping(value = "/sendJobs")
    public ResponseEntity<JsonResult> addJobs(@ApiParam(name = "json", value = "职位对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        Job job = JsonUtils.jsonToPojo(json, Job.class);
        //添加职位
        if (recruitersService.addJobs(job)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("发布职位成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("发布职位失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询发布职位", httpMethod = "post")
    @PostMapping("/selectJobsByStatus")
    public ResponseEntity<JsonResult> selectJobsByStatus(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                         @ApiParam(name = "page", value = "分页数字", required = true) Integer page,
                                                         @ApiParam(name = "status", value = "发布的职位状态", required = true) Integer status,
                                                         @ApiParam(name = "all", value = "条件为全部", required = true) boolean all) {
        JsonResult jsonResult = new JsonResult();
        List<Job> jobList;
        if (all) {
            jobList = recruitersService.selectAllJobsById(userId, page);
        } else {
            jobList = recruitersService.selectJobsByStatus(userId, page, status);
        }
        if (jobList != null && jobList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(jobList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "收藏牛人简历", httpMethod = "post")
    @PostMapping("/addCollectResume")
    public ResponseEntity<JsonResult> addCollectResume(@ApiParam(name = "json", value = "收藏简历对象json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        CollectResume collectResume = JsonUtils.jsonToPojo(json, CollectResume.class);
        if (recruitersService.addCollectResume(collectResume)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询符合条件的牛人简历", httpMethod = "post")
    @PostMapping("/selectResume")
    public ResponseEntity<JsonResult> selectResume(@ApiParam(name = "name", value = "简历名字，模糊查询", required = true) String name,
                                                   @ApiParam(name = "workAge", value = "工作经验年数", required = true) Integer workAge,
                                                   @ApiParam(name = "education", value = "学历要求", required = true) Integer education,
                                                   @ApiParam(name = "salaryRange", value = "薪资要求", required = true) Integer salaryRange,
                                                   @ApiParam(name = "page", value = "分页数", required = true) Integer page,
                                                   @ApiParam(name = "weakTime", value = "当前请求时间", required = true) Date weakTime) {
        JsonResult jsonResult = new JsonResult();
        List<Resume> resumeList = recruitersService.selectResumeByNameAndworkAgeAndeducationAndsalaryRange(
                name, workAge, education, salaryRange, page, weakTime);
        if (resumeList != null && resumeList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(resumeList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }
}