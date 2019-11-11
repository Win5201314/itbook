package com.zsl.boss.demo.controller;

import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.entity.*;
import com.zsl.boss.demo.service.BaseService;
import com.zsl.boss.demo.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//参考博文，Swagger注解详解 https://blog.csdn.net/u014231523/article/details/76522486
@Api(value = "更新应聘和招聘用户基本信息公共接口", tags = "更新应聘和招聘用户基本信息公共接口")
@RestController
public class BaseMsgController {

    @Autowired
    BaseService baseService;

    @ApiOperation(value = "更新头像", httpMethod = "post")
    @PostMapping("/updateHeadimgurl")
    public ResponseEntity<JsonResult> updateHeadimgurl(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                       @ApiParam(name = "headimgurl", value = "头像地址", required = true) String headimgurl,
                                                       @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updateHeadimgurl(userId, headimgurl, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新用户名", httpMethod = "post")
    @PostMapping("/updateUserName")
    public ResponseEntity<JsonResult> updateUserName(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                     @ApiParam(name = "name", value = "新用户名字", required = true) String name,
                                                     @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updateUserName(userId, name, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新性别", httpMethod = "post")
    @PostMapping("/updateSex")
    public ResponseEntity<JsonResult> updateSex(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                @ApiParam(name = "sex", value = "性别", required = true) String sex,
                                                @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updateSex(userId, sex, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新微信号", httpMethod = "post")
    @PostMapping("/updateWxAccount")
    public ResponseEntity<JsonResult> updateWxAccount(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                      @ApiParam(name = "wxAccount", value = "微信号", required = true) String wxAccount,
                                                      @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updateWxAccount(userId, wxAccount, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "更新位置", httpMethod = "post")
    @PostMapping("/updateLocation")
    public ResponseEntity<JsonResult> updateLocation(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                     @ApiParam(name = "longitude", value = "经度", required = true) float longitude,
                                                     @ApiParam(name = "latitude", value = "纬度", required = true) float latitude,
                                                     @ApiParam(name = "weakTime", value = "再次使用时间", required = true) Date weakTime,
                                                     @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updateLocation(userId, longitude, latitude, type, weakTime)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }


    @ApiOperation(value = "绑定微信", httpMethod = "post")
    @PostMapping("/bindingOpenid")
    public ResponseEntity<JsonResult> bindingOpenid(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                    @ApiParam(name = "openid", value = "微信唯一标识", required = true) String openid,
                                                    @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.bindingOpenid(userId, openid, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "设置密码, 如果没有密码，第一次", httpMethod = "post")
    @PostMapping("/setPassword")
    public ResponseEntity<JsonResult> updatePassword(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                  @ApiParam(name = "password", value = "密码", required = true) String password,
                                                  @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (baseService.updatePassword(userId, password, type)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("更新成功");
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("更新失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "修改手机号", httpMethod = "post")
    @PostMapping("/updatePhone")
    public ResponseEntity<JsonResult> updatePhone(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                  @ApiParam(name = "phoneNumber", value = "手机号", required = true) String phoneNumber,
                                                  @ApiParam(name = "code", value = "验证码", required = true) String code,
                                                  @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        //获取redis缓存中
        if (new RedisUtil().hasKey(phoneNumber)) {
            String redisCode = (String) new RedisUtil().get(phoneNumber);
            if (redisCode.equals(code)) {
                //及时清除掉缓存数据
                new RedisUtil().del(phoneNumber);
                if (baseService.updatePhone(userId, phoneNumber, type)) {
                    jsonResult.setStatus(Status.SUCCESS);
                    jsonResult.setResult("更新成功");
                }
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("验证码不一致");
            }
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("请重新获取验证码");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "查找所有的打招呼用语", httpMethod = "post")
    @PostMapping("/selectAllGreetWord")
    public ResponseEntity<JsonResult> selectAllGreetWord(@ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        List<String> words = baseService.selectAllGreetWord(type);
        if (words != null && words.size() > 0) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(words);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("获取失败");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "沟通过的", httpMethod = "post")
    @PostMapping(value = "/talked")
    public ResponseEntity<JsonResult> talked(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                             @ApiParam(name = "page", value = "分页数字", required = true) Integer page,
                                             @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        //应聘方
        if (type == 1) {
            List<TalkedJob> talkedJobList = baseService.selectTalkedJob(userId, page);
            if (talkedJobList != null && talkedJobList.size() > 0) {
                List<Job> jobList = new ArrayList<>();
                for (TalkedJob talkedJob : talkedJobList) {
                    int jobId = talkedJob.getJobId();
                    Job job = baseService.selectJobById(jobId);
                    jobList.add(job);
                }
                jsonResult.setStatus(Status.SUCCESS);
                jsonResult.setResult(jobList);
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("没有数据");
            }
        } else {
           //招聘方
            List<TalkedResume> talkedResumeList = baseService.selectTalkedResume(userId, page);
            if (talkedResumeList != null && talkedResumeList.size() > 0) {
                List<Resume> resumeList = new ArrayList<>();
                for (TalkedResume talkedResume : talkedResumeList) {
                    int resumeId = talkedResume.getResumeId();
                    Resume resume = baseService.selectResumeById(resumeId);
                    resumeList.add(resume);
                }
                jsonResult.setStatus(Status.SUCCESS);
                jsonResult.setResult(resumeList);
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("没有数据");
            }
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "待面试", httpMethod = "post")
    @PostMapping(value = "/forTheInterview")
    public ResponseEntity<JsonResult> selectInterview(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                                      @ApiParam(name = "page", value = "分页数字", required = true) Integer page,
                                                      @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        List<Interview> interviewList = baseService.selectInterviewById(userId, page, type);
        if (interviewList != null && interviewList.size() > 0) {
            List<InterviewExt> interviewExtList = new ArrayList<>();
            for (Interview interview : interviewList) {
                int uid = interview.getUserId();
                int recruiterId = interview.getRecruiterId();
                int jobId = interview.getJobId();
                InterviewExt interviewExt = baseService.selectInterviewExt(uid, recruiterId, jobId);
                interviewExtList.add(interviewExt);
            }
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult(interviewExtList);
        } else {
            jsonResult.setStatus(Status.FAIL);
            jsonResult.setResult("没有数据");
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "收藏", httpMethod = "post")
    @PostMapping(value = "/selectCollect")
    public ResponseEntity<JsonResult> selectCollect(@ApiParam(name = "userId", value = "用户主键id", required = true) Integer userId,
                                              @ApiParam(name = "page", value = "分页数字", required = true) Integer page,
                                              @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type) {
        JsonResult jsonResult = new JsonResult();
        if (type == 1) {
            //应聘方
            List<CollectJob> collectJobList = baseService.selectCollectJobById(userId, page);
            if (collectJobList != null && collectJobList.size() > 0) {
                List<Job> jobList = new ArrayList<>();
                for (CollectJob collectJob : collectJobList) {
                    int jobId = collectJob.getJobId();
                    Job job = baseService.selectJobById(jobId);
                    jobList.add(job);
                }
                jsonResult.setStatus(Status.SUCCESS);
                jsonResult.setResult(jobList);
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("没有数据");
            }
        } else {
            //招聘方
            List<CollectResume> collectResumeList = baseService.selectCollectResums(userId, page);
            if (collectResumeList != null && collectResumeList.size() > 0) {
                List<Resume> resumeList = new ArrayList<>();
                for (CollectResume collectResume : collectResumeList) {
                    int resumeId = collectResume.getResumeId();
                    Resume resume = baseService.selectResumeById(resumeId);
                    resumeList.add(resume);
                }
                jsonResult.setStatus(Status.SUCCESS);
                jsonResult.setResult(resumeList);
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("没有数据");
            }
        }
        return ResponseEntity.ok(jsonResult);
    }

    @ApiOperation(value = "举报", httpMethod = "post")
    @PostMapping("/report")
    public ResponseEntity<JsonResult> report(@ApiParam(name = "userId", value = "应聘方id", required = true) Integer userId,
                                             @ApiParam(name = "resumeId", value = "简历id") Integer resumeId,
                                             @ApiParam(name = "recruiterId", value = "招聘方id", required = true) Integer recruiterId,
                                             @ApiParam(name = "type", value = "应聘方 = 1，招聘方 = 2", required = true) Integer type,
                                             @ApiParam(name = "content",value = "举报内容", required = true) String content,
                                             @ApiParam(name = "typePort", value = "举报类型", required = true) Integer typePort,
                                             @ApiParam(name = "imageUrl", value = "举报图片的地址", required = true) String imageUrl) {
        return null;
    }
}
