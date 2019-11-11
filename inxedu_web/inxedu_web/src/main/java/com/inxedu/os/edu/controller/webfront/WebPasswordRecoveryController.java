package com.inxedu.os.edu.controller.webfront;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.mobile.SmsUtil;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台　找回密码 controller
 * @author www.inxedu.com
 */
@Controller
public class WebPasswordRecoveryController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(WebPasswordRecoveryController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private EmailService emailService;
	/**
	 * 跳转找回密码页面
	 */
	@RequestMapping("/front/passwordRecovery")
	public String passWordRecovery(){
		return getViewPath("/web/user/password-recovery");
	}

	//到修改密码页面
	@RequestMapping("/front/updatepwd")
	public String updatepwd(HttpServletRequest request, @RequestParam String token){
		try{
			//判断token 是否存在不存在则跳到找回密码页面
			User uesr = (User) CacheUtil.get(token);
			if(ObjectUtils.isNull(uesr)){
				return "redirect:/front/passwordRecovery";
			}
			request.setAttribute("uesr",uesr);
			request.setAttribute("token",token);
		}catch (Exception e) {
			logger.error("updatepwd()---error",e);
			setExceptionRequest(request, e);
		}
		return getViewPath("/web/user/update-pwd");

	}

	//修改密码
	@RequestMapping("/front/changePwd")
	@ResponseBody
	public Map<String,Object> changePwd(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String token = request.getParameter("token")==null?"":request.getParameter("token");
			String oldPassword = request.getParameter("oldpwd")==null?"":request.getParameter("oldpwd");
			String newPassword = request.getParameter("newpwd")==null?"":request.getParameter("newpwd");
			//判断token和密码是否为空
			if(StringUtils.isEmpty(token)||StringUtils.isEmpty(newPassword)){
				json = this.setJson(false, "参数不正确", null);
				return json;
			}
			//判断密码格式
			if(!WebUtils.isPasswordAvailable(newPassword)){
				json = this.setJson(false, "密码只能是数字字母组合且大于等于6位小于等于16位", null);
				return json;
			}
			//判断两次输入的密码是否相同
			if(!newPassword.equals(oldPassword)){
				json = this.setJson(false,"新密码和确认密码不一致",null);
				return json;
			}
			User uesr = (User) CacheUtil.get(token);
			if(ObjectUtils.isNull(uesr)){
				json = this.setJson(false, "token已过期", null);
				return json;
			}

			//修改密码
			User user= new User();
			user.setUserId(uesr.getUserId());
			user.setPassword(MD5.getMD5(newPassword));
			userService.updateUserPwd(user);
			json = this.setJson(true, "修改成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("changePwd()--error",e);
		}
		return json;
	}
	/**
	 *获取手机找回密码验证码
	 */
	@RequestMapping("/front/ajax/getProve")
	@ResponseBody
	public Map<String,Object> getProve(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		try{
			String proveCode = request.getParameter("proveCode");
			String mobile = request.getParameter("mobile");
			if(mobile==null || mobile.trim().length()==0){
				json = setJson(false, "请填写手机号", null);
				return json;
			}
			String randCode = (String) request.getSession().getAttribute(CommonConstants.RAND_CODE);
			if(randCode==null ||randCode.trim().length()==0 || proveCode==null || proveCode.trim().length()==0 || !proveCode.equals(randCode) ){
				json = setJson(false, "图片验证码错误", null);
				return json;
			}

			User user = userService.queryUserByEmailOrMobile(mobile);
			if(user==null){
				json = setJson(false, "该手机号未注册", null);
				return json;
			}

			String mobileCode = WebUtils.getRandomNum(6);
			//System.out.println("生成手机验证码："+mobileCode);
			CacheUtil.set("mobileCode",mobileCode,300);
			/*指定模板发送短信*/
			SmsUtil smsUtil=new SmsUtil();
			/*获取对应的模板信息*/
			Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.message.toString());
			keywordmap = (Map<String, Object>) keywordmap.get(WebSiteProfileType.message.toString());
			Map<String,Object> msgConfig=(Map<String, Object>)keywordmap.get("recoverySms");
			smsUtil.setDestNumber(mobile);
			smsUtil.setCount(mobileCode);
			/*短信签名*/
			String sign = msgConfig.get("sign").toString();
			if(ObjectUtils.isNull(msgConfig.get("template"))){
				json = this.setJson(false,"短信发送失败，请联系管理员",null);
				return json;
			}else if("请选择模板".equals(msgConfig.get("template"))){
				json = this.setJson(false,"短信发送失败，请联系管理员",null);
				return json;
			}
			/*模板*/
			String template = msgConfig.get("template").toString();

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
		}catch (Exception e) {
			logger.error("getProve()---error",e);
			setExceptionRequest(request, e);
		}

		return json;
	}
	/**
	 * 邮箱手机找回密码 公共方法
	 */
	@RequestMapping("/front/ajax/findPassAjax")
	@ResponseBody
	public Map<String,Object> findPassByEmailOrMobile(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		try{
			//注册开关配置
			Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
			String emailRecovery=registerController.get("emailRecovery")+"";
			String mobileRecovery=registerController.get("mobileRecovery")+"";
			String emailOrMobile=request.getParameter("emailOrMobile");
			if("ON".equals(emailRecovery) && "ON".equals(mobileRecovery)){
				if(!WebUtils.checkMobile(emailOrMobile)&&!WebUtils.checkEmail(emailOrMobile,50)){
					json = setJson(false, "请填写正确的邮箱 / 手机号", null);
					return json;
				}else if(WebUtils.checkEmail(emailOrMobile,50)){
					return sendEmail(emailOrMobile,json,request);
				}
				else{
					return recoveryMobilePwd(emailOrMobile,json,request);
				}
			}else if("ON".equals(emailRecovery)){
				if(!WebUtils.checkEmail(emailOrMobile,50)){
					json = setJson(false, "请填写正确的邮箱", null);
					return json;
				}else{
					return sendEmail(emailOrMobile,json,request);
				}
			}
			else if("ON".equals(mobileRecovery)){
				if(!WebUtils.checkMobile(emailOrMobile)){
					json = setJson(false, "请填写正确的手机", null);
					return json;
				}else{
					return recoveryMobilePwd(emailOrMobile,json,request);
				}
			}
		}catch (Exception e) {
			logger.error("findPassByEmailOrMobile()---error",e);
			json=setAjaxException(json);
		}
		return json;
	}


	/**
	 * 邮箱找回密码 发送邮件
	 * @param email
	 * @param json
	 * @param request
     * @return
     */
	public Map<String,Object> sendEmail(String email,Map<String,Object> json,HttpServletRequest request){
		try{
			String pageCode = request.getParameter("pageCode");
			String randCode = (String) request.getSession().getAttribute(CommonConstants.RAND_CODE);
			if(randCode==null ||randCode.trim().length()==0 || pageCode==null || pageCode.trim().length()==0 || !pageCode.equals(randCode) ){
				json = this.setJson(false, "验证码错误", null);
				return json;
			}
			User user = userService.queryUserByEmailOrMobile(email);
			if(user==null){
				json = this.setJson(false, "该邮箱号未注册", null);
				return json;
			}
			//生成token
			String uuid = StringUtils.createUUID().replace("-", "");
			//放入缓存 5分钟
			CacheUtil.set(uuid,user,300);
			//给邮箱发送找回密码的邮件
			emailService.sendMail(email,"<div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\">亲爱的用户：<br><br>您的密码重设要求已经得到验证。请点击以下链接输入您新的密码：<br><br>"
					+CommonConstants.contextPath
					+ "/front/updatepwd?token="+uuid+"<br>" +
					"<br>如果您的email程序不支持链接点击，请将上面的地址拷贝至您的浏览器(例如IE)的地址栏进入。" +
					"<br><br>(这是一封自动产生的email，请勿回复。)<br><style type=\"text/css\">.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style></div>","找回密码" );

			json = this.setJson(true, "邮件发送成功，请登录邮箱查收", null);
			request.getSession().removeAttribute(CommonConstants.RAND_CODE);
		}catch (Exception e) {
			json=this.setAjaxException(json);
			logger.error("sendEmail()--error",e);
		}
		return json;
	}

	/**
	 * 验证手机验证码 跳转
	 * @param mobile
	 * @param json
	 * @param request
     * @return
     */
	public Map<String,Object> recoveryMobilePwd(String mobile,Map<String, Object> json,HttpServletRequest request){
		try{
			if(ObjectUtils.isNull(CacheUtil.get("mobileCode"))){
				json = setJson(false, "请先获取验证码", null);
				return json;
			}
			String mobileProveCode = CacheUtil.get("mobileCode").toString();
			String mobileProve = request.getParameter("mobileCode");
			if(mobile==null || mobile.trim().length()==0){
				json = setJson(false, "请填写手机号", null);
				return json;
			}
			if(mobileProve==null || mobileProve.trim().length()==0){
				json = setJson(false, "请填写验证码", null);
				return json;
			}
			if(!mobileProve.equals(mobileProveCode)){
				json =setJson(false,"手机验证码错误",null);
				return json;
			}
			User user = userService.queryUserByEmailOrMobile(mobile);
			if(user==null){
				json = setJson(false, "该手机号未注册", null);
				return json;
			}
			/*生成token*/
			String uuid = StringUtils.createUUID().replace("-","");
			/*把token和用户信息放入缓存*/
			CacheUtil.set(uuid,user,300);
			json = setJson(true,"uuid",uuid);
		}catch (Exception e) {
			logger.error("recoveryMobilePwd()---error",e);
			json=setAjaxException(json);
		}
		return json;
	}
}
