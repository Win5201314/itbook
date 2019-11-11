package com.inxedu.os.edu.controller.appapi;

import com.alibaba.fastjson.JSONObject;
import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.WebLoginUser;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * app接口 Controller
 * @author www.inxedu.com
 */
@Controller
public class InxeduApiController extends BaseController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InxeduApiController.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private UserService userService;
	@InitBinder({"user"})
	public void initBinderUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	/**
	 * 根据用户key 获得登录用户（用于其他平台登陆登录）
	 */
	@RequestMapping("/user/ajax/logined")
	@ResponseBody
	public Object getLoginUserByKey(HttpServletRequest request, @RequestParam String userKey) {
		Map<String,Object> json = new HashMap<String,Object>();
		User user = (User) CacheUtil.get(userKey);
		if(ObjectUtils.isNotNull(user)){
			//添加需要的属性
			WebLoginUser webLoginUser=new WebLoginUser();
			webLoginUser.setId(Long.valueOf(user.getUserId()));
			webLoginUser.setCusId(Long.valueOf(user.getUserId()));
			webLoginUser.setEmail(user.getEmail());
			webLoginUser.setGender(user.getSex());
			webLoginUser.setId(Long.valueOf(user.getUserId()));
			webLoginUser.setMobile(user.getMobile());
			webLoginUser.setNickname(user.getShowName());
			webLoginUser.setRealname(user.getUserName());
			webLoginUser.setAvatar(user.getPicImg());
			webLoginUser.setUserInfo("这个人很懒，他还没有签名");

			JSONObject jsonObject = FastJsonUtil.obj2JsonObject(webLoginUser);
			json = this.setJson(true, "", jsonObject.toString());
		}else{
			json = this.setJson(false, "", "");
		}

		return json;
	}
	/**
	 * 注册控制开关
	 */
	@RequestMapping("api/registerSwitch")
	@ResponseBody
	public Map<String,Object> registerController(HttpServletRequest request){
		Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
		Map<String,Object> json = new HashMap<String,Object>();
		json = this.setJson(true,"",registerController);
		return json;
	}
	/**
	 * 创建学员
	 */
	@RequestMapping("/api/user/register")
	@ResponseBody
	public Map<String,Object> createUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			json = userService.createDoRegister(request,response,user);
		}catch (Exception e) {
			logger.error("createUser()--eror",e);
		}
		return json;
	}

	/**
	 * 根据用户key 获得后台登录用户（用于其他平台登陆登录）
	 */
	@RequestMapping("/sysuser/ajax/logined")
	@ResponseBody
	public Object getLoginSysUserByKey(HttpServletRequest request, @RequestParam String userKey) {
		Map<String,Object> json = new HashMap<String,Object>();
		SysUser sysUser = (SysUser) CacheUtil.get(userKey);
		if(ObjectUtils.isNotNull(sysUser)){
			JSONObject jsonObject = FastJsonUtil.obj2JsonObject(sysUser);
			json = this.setJson(true, "", jsonObject.toString());
		}else{
			json = this.setJson(false, "", "");
		}
		return json;
	}


	/**
	 * 执行登录（用于其他平台登陆登录）
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/api/ajax/userLogin")
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String ipForget = request.getParameter("ipForget");
			if(!StringUtils.isNotEmpty(account)){
				json = this.setJson(false, "请输入登录帐号", null);
				return json;
			}
			if(!StringUtils.isNotEmpty(password)){
				json = this.setJson(false, "请输入登录密码", null);
				return json;
			}
			User user = userService.getLoginUser(account, MD5.getMD5(password));
			if(user==null){
				json = this.setJson(false, "帐号或密码错误", null);
				return json;
			}
			Map map = new HashMap();
			map.put("account",account);
			map.put("password",password);
			map.put("ipForget",ipForget);
			map.put("request",request);
			map.put("response",response);
			map = userService.queryDoUserLogin(map);
			json = this.setJson((boolean)map.get("success"), (String)map.get("message"), map);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("userLogin()--error",e);
		}
		return json;
	}

}
