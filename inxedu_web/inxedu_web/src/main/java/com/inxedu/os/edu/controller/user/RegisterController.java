package com.inxedu.os.edu.controller.user;



import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.controller.webfront.WebPasswordRecoveryController;
import com.inxedu.os.edu.service.mobile.SmsUtil;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/8/8.
 */
@Controller
public class RegisterController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(WebPasswordRecoveryController.class);
    @Autowired
    private EmailService emailService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private UserService userService;
    //发送邮箱验证码
    @RequestMapping("/createuser/ajax/sendEmailRegister")
    @ResponseBody
    public Map<String,Object> sendEmailRegister(HttpServletRequest request) {
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());

        try{
            String randomcodeReg = request.getParameter("randomcodeReg");
            if(StringUtils.isEmpty(randomcodeReg)){
                json = this.setJson(false,"验证码不能为空！",null);
                return json;
            }

            //获得验证码
            Object randCode = request.getSession().getAttribute(CommonConstants.RAND_CODE);
            if(ObjectUtils.isNull(randCode)||!randCode.equals(randomcodeReg) ){
                json = this.setJson(false,"请输入正确的验证码！",null);
                return json;
            }

            //获取邮箱
            String email = request.getParameter("emailVal");
            if(StringUtils.isEmpty(email)){
                json = this.setJson(false,"邮箱不能为空！",null);
                return json;
            }
            if(userService.checkEmail(email)){
                json = this.setJson(false, "该邮箱已被使用", null);
                return json;
            }

            //验证码请求的次数限制
            Object o = registerController.get("ipLimit");
            Object o2 = registerController.get("emailLimit");
            int limit = Integer.parseInt(o.toString());
            int emailLimit =Integer.parseInt(o2.toString());
            String ip = null;
            //获取用户ip
            ip= request.getLocalAddr();
            String trueUrl = CommonConstants.contextPath;
            String referer = request.getHeader("referer");
           /* 判断referer是否为官方地址*/
            if (!WebUtils.compareReferer(referer,trueUrl)){
                json = this.setJson(false,"请到官方网站注册信息！",null);
                return json;
            }
            /*判断注册验证次数是否达到上限*/
            boolean registerLimit = WebUtils.registerLimit(email,ip,emailLimit,limit);
            if (!registerLimit){
                json = this.setJson(false, "发送次数过多，请明天再试", null);
                return json;
            }else {
                String emailCodeNum = "";
                //获得验证码
                emailCodeNum = WebUtils.getRandomNum(4);
                HttpSession session = request.getSession();
                //把验证码放入缓存，有效时间60秒
                CacheUtil.set(session.getId()+"_emailCodeNum",emailCodeNum,300);

                json = this.setJson(true, "邮件发送成功，请登录邮箱查收", null);
                emailService.sendMail(email,"验证码是"+emailCodeNum+"，有效时间为5分钟。",email);
                System.out.println("邮箱验证码"+emailCodeNum);
                return json;
            }
        }catch (Exception e) {
            this.setAjaxException(this.setJson(false, "失败", null));
            logger.error("sendEmailRegister()--error",e);
        }
        return json;

    }
    //发送手机验证码
    @RequestMapping("/createuser/ajax/sendPhoneRegister")
    @ResponseBody
    public Map<String,Object> sendPhoneRegister(HttpServletRequest request) {
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
        SmsUtil smsUtil=new SmsUtil();

        try{
            String randomcodeReg = request.getParameter("randomcodeReg");
            if(StringUtils.isEmpty(randomcodeReg)){
                json = this.setJson(false,"验证码不能为空！",null);
                return json;
            }

            //获得验证码
            Object randCode = request.getSession().getAttribute(CommonConstants.RAND_CODE);
            if(randCode==null|| !randomcodeReg.equals(randCode) ){
                json = this.setJson(false,"请输入正确的验证码！",null);
                return json;
            }
            //获取手机号
            String mobile = request.getParameter("mobileVal");
            if(StringUtils.isEmpty(mobile)){
                json = this.setJson(false,"手机号码不能为空！",null);
                return json;
            }
            if(userService.checkMobile(mobile)){
                json = this.setJson(false, "该手机号已被使用", null);
                return json;
            }

            //验证码请求的次数限制
            int limit = Integer.parseInt(registerController.get("ipLimit").toString());
            int mobileLimit = Integer.parseInt(registerController.get("mobileLimit").toString());
            String referer = request.getHeader("referer");
            String trueUrl = CommonConstants.contextPath;
            /* 判断referer是否为官方地址*/
            if (!WebUtils.compareReferer(referer,trueUrl)){
                json = this.setJson(false,"请到官方网站注册信息！",null);
                return json;
            }
            String ip = null;
            //获取用户ip
            ip= request.getLocalAddr();
            /*判断注册验证次数是否达到上限*/
            boolean registerLimit = WebUtils.registerLimit(mobile,ip,mobileLimit,limit);
            if (!registerLimit){
                json = this.setJson(false, "发送次数过多，请明天再试", null);
                return json;
            }else {
                String mobileCodeNum = "";
                //获得验证码
                mobileCodeNum = WebUtils.getRandomNum(4);
                HttpSession session = request.getSession();
                //把验证码放入缓存
                CacheUtil.set(session.getId()+"_mobileCodeNum",mobileCodeNum,300);
                /*获取对应的模板信息*/
                Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.message.toString());
                keywordmap = (Map<String, Object>) keywordmap.get(WebSiteProfileType.message.toString());
                Map<String,Object> msgConfig=(Map<String, Object>)keywordmap.get("registerMsg");
                smsUtil.setDestNumber(mobile);
                //验证码
                smsUtil.setCount(mobileCodeNum);
                /*短信签名*/
                String sign = msgConfig.get("sign").toString();
                if(ObjectUtils.isNull(msgConfig.get("template"))){
                    json = this.setJson(false,"短信发送失败，请联系管理员",null);
                    return json;
                }else if("请选择模板".equals(msgConfig.get("template"))){
                    json = this.setJson(false,"短信发送失败，请联系管理员",null);
                    return json;
                }
                /*短信模板*/
                String template = msgConfig.get("template").toString();

                //腾讯云短信模板的id
                int tpl_id = Integer.parseInt(template);
                smsUtil.setTpl_id(tpl_id);
                smsUtil.setSign(sign);
                String result = smsUtil.sendmsgPoint();
                Map<String,Object> returnMes = FastJsonUtil.json2Map(result);
                if (returnMes!=null&&Integer.parseInt(returnMes.get("result").toString())==0){
                    request.getSession().removeAttribute(CommonConstants.RAND_CODE);
                    json = this.setJson(true, "短信发送成功，请查收", null);
                    return json;
                }
                json = this.setJson(false, "短信发送失败，请重试", null);
                System.out.println("手机验证码"+mobileCodeNum);
            }
        }catch (Exception e) {
            this.setAjaxException(json);
            logger.error("sendEmailRegister()--error",e);
        }
        return json;
    }
}
