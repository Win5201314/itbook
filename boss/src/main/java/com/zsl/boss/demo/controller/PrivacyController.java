package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.Company;
import com.zsl.boss.demo.entity.ProjectExpectation;
import com.zsl.boss.demo.entity.ShieldCompany;
import com.zsl.boss.demo.service.UserService;
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

import java.util.List;

@Api(value = "应聘方隐私设置", tags = "隐私设置接口")
@RestController
public class PrivacyController {

    private Logger logger = LoggerFactory.getLogger(PrivacyController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "是否隐藏简历", httpMethod = "post")
    @PostMapping("/ishideResume")
    public ResponseEntity<JsonResult> updateIsHideResume(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                 @ApiParam(name = "isHideResume", value = "是否隐藏简历", required = true) Boolean isHideResume) {
        JsonResult jsonResult = new JsonResult();
        //是否更新成功
        if (userService.updateIsHideResume(userId, isHideResume)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "是否对猎头隐藏简历", httpMethod = "post")
    @PostMapping("/ishideResumeToHeadhunter")
    public ResponseEntity<JsonResult> updateIshideResumeToHeadhunter(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                 @ApiParam(name = "isHideResumeToHeadhunter", value = "是否对猎头隐藏简历", required = true) Boolean isHideResumeToHeadhunter) {
        JsonResult jsonResult = new JsonResult();
        //是否更新成功
        if (userService.updateIshideResumeToHeadhunter(userId, isHideResumeToHeadhunter)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "不看猎头职位", httpMethod = "post")
    @PostMapping("/isLookHeadhunterJobs")
    public ResponseEntity<JsonResult> updateIsLookHeadhunterJobs(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                 @ApiParam(name = "isLookHeadhunterJobs", value = "不看猎头职位", required = true) Boolean isLookHeadhunterJobs) {
        JsonResult jsonResult = new JsonResult();
        //是否更新成功
        if (userService.updateIsLookHeadhunterJobs(userId, isLookHeadhunterJobs)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "是否允许加密电话联系我", httpMethod = "post")
    @PostMapping("/isEncryptionTelephoneCanCallMe")
    public ResponseEntity<JsonResult> updateIsEncryptionTelephoneCanCallMe(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                 @ApiParam(name = "isEncryptionTelephoneCanCallMe", value = "是否允许加密电话联系我", required = true) Boolean isEncryptionTelephoneCanCallMe) {
        JsonResult jsonResult = new JsonResult();
        //是否更新成功
        if (userService.updateIsEncryptionTelephoneCanCallMe(userId, isEncryptionTelephoneCanCallMe)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "添加屏蔽的公司", httpMethod = "post")
    @PostMapping("/addShieldCompany")
    public ResponseEntity<JsonResult> addShieldCompany(@ApiParam(name = "shieldCompany", value = "屏蔽的公司json", required = true) String json) {
        JsonResult jsonResult = new JsonResult();
        ShieldCompany shieldCompany = JsonUtils.jsonToPojo(json, ShieldCompany.class);
        if (userService.addShieldCompany(shieldCompany)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("添加成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("添加失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "搜索屏蔽的公司", httpMethod = "post")
    @PostMapping("/selectShieldCompany")
    public ResponseEntity<JsonResult> selectShieldCompany(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                          @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<ShieldCompany> shieldCompanyList = userService.selectShieldCompany(userId, page);
        if (shieldCompanyList != null && shieldCompanyList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(shieldCompanyList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查询公司", httpMethod = "post")
    @PostMapping("/selectCompanyByName")
    public ResponseEntity<JsonResult> selectCompanyByName(@ApiParam(name = "companyName", value = "公司名字，大概名字，模糊查询", required = true) String companyName,
                                                          @ApiParam(name = "page", value = "分页数字", required = true) Integer page) {
        JsonResult jsonResult = new JsonResult();
        List<Company> companyList = userService.selectCompanyByName(companyName, page);
        if (companyList != null && companyList.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(companyList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }
}
