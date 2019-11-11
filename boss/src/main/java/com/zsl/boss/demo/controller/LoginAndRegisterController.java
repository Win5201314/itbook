package com.zsl.boss.demo.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zsl.boss.demo.common.JsonResult;
import com.zsl.boss.demo.common.Status;
import com.zsl.boss.demo.util.AliyunSmsUtils;
import com.zsl.boss.demo.service.UserService;
import com.zsl.boss.demo.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户注册登录和注册相关的一些处理接口", tags = "用户控制器")
@RestController
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);

    /**
     * 注册登录一体化，接收手机号和验证码
     * @param phoneNumber 手机号
     * @param code 验证码
     * @return
     */
    @ApiOperation(value = "注册登录一体化，接收手机号和验证码", notes = "手机号和验证码一致即可进去")
    @PostMapping("/loginAndRegister")
    public ResponseEntity<JsonResult> loginAndRegister(@ApiParam(name = "phoneNumber", value = "手机号码", required = true) String phoneNumber,
                                                       @ApiParam(name = "code", value = "验证码", required = true) String code) {
        JsonResult jsonResult = new JsonResult();
        //获取redis缓存中
        if (new RedisUtil().hasKey(phoneNumber)) {
            String redisCode = (String) new RedisUtil().get(phoneNumber);
            if (redisCode.equals(code)) {
                //将手机号放到用户表，如果是新用户
                if (userService.insertOrUpdateUser(phoneNumber)) {
                    jsonResult.setStatus(Status.SUCCESS);
                    jsonResult.setResult("认证通过");
                    //及时清除掉缓存数据
                    new RedisUtil().del(phoneNumber);
                } else {
                    jsonResult.setStatus(Status.FAIL);
                    jsonResult.setResult("服务器插入数据失败");
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

    /**
     * 发送短信验证码，用于注册登录和忘记密码
     * 本机不支持密码那种，记住密码体验不好，没人愿意去记住，显得多余且体验差
     * @param phoneNumber 手机号
     * @return 返回是否发送成功
     */
    @ApiOperation(value = "发送短信验证码，用于注册登录和忘记密码")
    @PostMapping("/sendSMS")
    public ResponseEntity<JsonResult> sendSMS(@ApiParam(name = "phoneNumber", value = "手机号码", required = true) String phoneNumber) {
        AliyunSmsUtils.setNewcode();
        String code = Integer.toString(AliyunSmsUtils.getNewcode());
        logger.info("发送的验证码为：" + code);
        JsonResult jsonResult = new JsonResult();
        //发短信
        SendSmsResponse response = null;
        try {
            response = AliyunSmsUtils.sendSms(phoneNumber, code);
            logger.info("短信接口返回的数据----------------");
            logger.info("Code=" + response.getCode());
            logger.info("Message=" + response.getMessage());
            logger.info("RequestId=" + response.getRequestId());
            logger.info("BizId=" + response.getBizId());
            if (response.getCode() != null && response.getCode().equals("OK")) {
                jsonResult.setStatus(Status.SUCCESS);
                jsonResult.setResult("发送验证码成功!");
                //将手机号和验证码缓存到redis中，时间按照设定的5分钟有效期
                new RedisUtil().set(phoneNumber, code, 5 * 60);
            } else {
                jsonResult.setStatus(Status.FAIL);
                jsonResult.setResult("阿里云获取验证码失败!");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            jsonResult.setResult(Status.FAIL);
            jsonResult.setResult("服务器访问阿里云出错!");
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 微信登录
     * 如果数据库中有，则直接进去，否则，需要绑定手机号
     * @param openid 微信登录唯一标识
     * @return
     */
    @ApiOperation(value = "微信登录")
    @PostMapping("/wxLogin")
    public ResponseEntity<JsonResult> wxLogin(@ApiParam(name = "openid", value = "微信唯一标识openid", required = true) String openid) {
        JsonResult jsonResult = new JsonResult();
        if (userService.isHaveOpenid(openid)) {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("有openid");
        } else {
            jsonResult.setStatus(Status.SUCCESS);
            jsonResult.setResult("没有openid");
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 微信登录绑定手机号
     * @param phoneNumber 手机号
     * @param code 验证码
     * @param openid 微信登录唯一标识
     * @return
     */
    @ApiOperation(value = "微信登录绑定手机号", notes = "手机号和验证码一致即可进去")
    @PostMapping("/registerByOpenid")
    public ResponseEntity<JsonResult> registerByOpenid(@ApiParam(name = "phoneNumber", value = "手机号码", required = true) String phoneNumber,
                                                       @ApiParam(name = "code", value = "验证码", required = true) String code,
                                                       @ApiParam(name = "openid", value = "微信唯一标识", required = true) String openid) {
        JsonResult jsonResult = new JsonResult();
        //获取redis缓存中
        if (new RedisUtil().hasKey(phoneNumber)) {
            String redisCode = (String) new RedisUtil().get(phoneNumber);
            if (redisCode.equals(code)) {
                //将手机号放到用户表，如果是新用户
                if (userService.insertOrUpdateUserWithOpenid(phoneNumber, openid)) {
                    jsonResult.setStatus(Status.SUCCESS);
                    jsonResult.setResult("认证通过");
                    //及时清除掉缓存数据
                    new RedisUtil().del(phoneNumber);
                } else {
                    jsonResult.setStatus(Status.FAIL);
                    jsonResult.setResult("服务器插入数据失败");
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

}
