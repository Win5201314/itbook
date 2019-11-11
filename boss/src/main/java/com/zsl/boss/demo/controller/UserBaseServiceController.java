package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.AttentionCompany;
import com.zsl.boss.demo.entity.Company;
import com.zsl.boss.demo.entity.Job;
import com.zsl.boss.demo.service.UserService;
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

@Api(value = "应聘用户一些基本服务的处理接口", tags = "应聘用户一些基本服务的处理接口")
@RestController
public class UserBaseServiceController {

    private Logger logger = LoggerFactory.getLogger(UserBaseServiceController.class);

    @Autowired
    UserService userService;

    /*@ApiOperation(value = "已投简历", httpMethod = "post")
    @PostMapping(value = "/selectAlreadyHaveResume")
    public ResponseEntity<JsonResult> selectAlreadyHaveResume(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                        @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        return null;
    }*/

    @ApiOperation(value = "我关注的公司", httpMethod = "post")
    @PostMapping(value = "/selectAttentionCompany")
    public ResponseEntity<JsonResult> selectAttentionCompany(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                        @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        //先查询中间表
        List<AttentionCompany> attentionCompanyList = userService.selectAttentionCompany(userId, page);
        if (attentionCompanyList != null && attentionCompanyList.size() > 0) {
            List<Company> companyList = new ArrayList<>();
            for (AttentionCompany attentionCompany : attentionCompanyList) {
                int companyId = attentionCompany.getCompanyId();
                Company company = userService.selectCompanyById(companyId);
                if (company != null) companyList.add(company);
            }
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(companyList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加关注的公司", httpMethod = "post")
    @PostMapping("/addAttentionCompany")
    public ResponseEntity<JsonResult> addAttentionCompany(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                          @ApiParam(name = "companyId", value = "被关注公司的id", required = true) Integer companyId) {
        JsonResult jsonResult = new JsonResult();
        //添加关注的公司
        if (userService.addAttentionCompany(userId, companyId)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "筛选公司，条件为：所属行业，公司规模，融资阶段")
    @PostMapping("/selectCompany")
    public ResponseEntity<JsonResult> selectCompany(@ApiParam(name = "tmt", value = "所属行业", required = true) String tmt,
                                                    @ApiParam(name = "scale", value = "公司规模", required = true) String scale,
                                                    @ApiParam(name = "financingStage", value = "融资阶段", required = true) String financingStage) {
        JsonResult jsonResult = new JsonResult();
        List<Company> companyList = userService.selectCompanyByTmtAndScaleAndFinancingStage(tmt, scale, financingStage);
        if (companyList != null && companyList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(companyList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "筛选公司，条件为：公司的热度值排名")
    @PostMapping("/selectCompanyByHot")
    public ResponseEntity<JsonResult> selectCompanyByHot() {
        return null;
    }

    @ApiOperation(value = "根据条件查询职位表", httpMethod = "post")
    @PostMapping("/selectJobs")
    public ResponseEntity<JsonResult> selectJobs(@ApiParam(name = "workPlace", value = "工作地址", required = true) String workPlace,
                                                 @ApiParam(name = "weakTime", value = "再次使用时间", required = true) Date weakTime,
                                                 @ApiParam(name = "name", value = "职位名字", required = true) String name,
                                                 @ApiParam(name = "education", value = "学历要求", required = true) String education,
                                                 @ApiParam(name = "salaryRange", value = "薪资范围 级别来区分,好计算", required = true) Integer salaryRange,
                                                 @ApiParam(name = "experience", value = "经验要求", required = true) Integer experience,
                                                 @ApiParam(name = "financingStage", value = "融资阶段", required = true) Integer financingStage,
                                                 @ApiParam(name = "scale", value = "公司规模", required = true) Integer scale,
                                                 @ApiParam(name = "page", value = "分页数", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<Job> jobList = userService.selectJobs(workPlace, weakTime, name, education, salaryRange, experience, financingStage, scale, page);
        if (jobList != null && jobList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(jobList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

}
