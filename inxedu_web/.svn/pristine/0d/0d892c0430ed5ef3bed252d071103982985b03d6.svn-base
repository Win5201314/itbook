package com.inxedu.os.edu.controller.system;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.system.QuerySysUser;
import com.inxedu.os.edu.entity.system.SysRole;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.system.SysUserLoginLog;
import com.inxedu.os.edu.service.system.SysRoleService;
import com.inxedu.os.edu.service.system.SysUserLoginLogService;
import com.inxedu.os.edu.service.system.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin/sysuser")
public class SysUserController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserLoginLogService sysUserLoginLogService;
	@InitBinder({"sysUser"})
	public void initBinderSysUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sysUser.");
	}
	@InitBinder({"querySysUser"})
	public void initBinderQuerySysUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("querySysUser.");
	}
	
	/**
	 * 查询用户登录日志
	 */
	@RequestMapping("/looklog/{userId}")
	public ModelAndView lookLoginLog(HttpServletRequest request,@PathVariable("userId") int userId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/admin/system/sys-user-loginlog"));
		try{
			page.setPageSize(10);
			List<SysUserLoginLog> logList = sysUserLoginLogService.queryUserLogPage(userId, page);
			model.addObject("logList", logList);
			model.addObject("userId", userId);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("lookLoginLog()--error",e);
		}
		return model;
	}
	
	/**
	 * 禁用或启用 和 删除用户(修改状态为2)
	 */
	@RequestMapping("/disableOrstart/{userId}/{type}")
	@ResponseBody
	public Map<String,Object> disableOrstart(HttpServletRequest request,@PathVariable("userId") int userId,@PathVariable("type") int type){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(type==3){
				//当前登录系统用户
				int loginUserId=SingletonLoginUtils.getLoginSysUserId(request);
				if(userId==loginUserId){
					json = this.setJson(false, "不能删除当前登录的用户", null);
					return json;
				}
			}
			if((type==1||type==2||type==3)&&userId>0){
				sysUserService.updateDisableOrstartUser(userId, type);
				json = this.setJson(true, "操作成功", null);
			}else{
				json = this.setJson(false, "操作失败", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("disableOrstart()--error",e);
		}
		return json;
	}

	/**
	 * 修改用户密码
	 */
	@RequestMapping("/updatepwd/{userId}")
	@ResponseBody
	public Map<String,Object> updateUserPew(HttpServletRequest request,@PathVariable("userId") int userId ){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String newPwd = request.getParameter("newPwd");
			if(userId>0 && newPwd!=null && newPwd.trim().length()>0){
				SysUser sysuser =new SysUser();
				sysuser.setUserId(userId);
				
				if(!WebUtils.isPasswordAvailable(newPwd)){
					json = this.setJson(false, "输入错误，密码可由“_”，数字，英文大于等6位小于等于16位", null);
					return json;
				}
				sysuser.setLoginPwd(MD5.getMD5(newPwd));
				sysUserService.updateUserPwd(sysuser);
				json = this.setJson(true, "密码修改成功", null);
			}else{
				json = this.setJson(false, "密码修改失败", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateUserPew()---error",e);
		}
		return json;
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping("/updateuser")
	@ResponseBody
	public Map<String,Object> updateSysUser(@ModelAttribute("sysUser") SysUser sysuser){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(sysuser.getUserId()>0){
				if(sysuser.getEmail()!=null && sysuser.getEmail().trim().length()>0 && !WebUtils.checkEmail(sysuser.getEmail(), 50)){
					json = this.setJson(false, "请输入正确的邮箱号", null);
					return json;
				}
				if(sysuser.getTel()!=null && sysuser.getTel().trim().length()>0 && !WebUtils.checkMobile(sysuser.getTel())){
					json = this.setJson(false, "请输入正确的电话号", null);
					return json;
				}
				sysUserService.updateSysUser(sysuser);
				json = this.setJson(true, "修改成功", null);
			}else{
				json = this.setJson(false, "修改失败", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("udpateSysUser()--error",e);
		}
		return json;
	}

	/**
	 * 初始化修改用户修改
	 */
	@RequestMapping("/initupdateuser/{userId}")
	@ResponseBody
	public Map<String,Object> initUpdateUser(@PathVariable("userId") int userId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			SysUser sysUser= sysUserService.querySysUserByUserId(userId);
			json = this.setJson(true, null, sysUser);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateUser()--error",e);
		}
		return json;
	}
	
	
	/**
	 * 创建用户
	 */
	@RequestMapping("/createuser")
	@ResponseBody
	public Map<String,Object> createSysUser(@ModelAttribute("sysUser") SysUser sysuser){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> resultJson = verifyUserData(sysuser);
			if(resultJson!=null){
				return resultJson;
			}
			boolean isExist = sysUserService.validateLoginName(sysuser.getLoginName());
			if(!isExist){
				json = this.setJson(false, "该登录名已经存在", null);
				return json;
			}
			sysuser.setLoginName(sysuser.getLoginName().trim());
			sysuser.setLoginPwd(MD5.getMD5(sysuser.getLoginPwd()));
			sysuser.setCreateTime(new Date());
			sysUserService.createSysUser(sysuser);
			json = this.setJson(true, "用户保存成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("createSysUser()--error",e);
		}
		return json;
	}
	/**
	 * 验证用户数据
	 */
	private Map<String,Object> verifyUserData(SysUser sysuser) {
		Map<String,Object> json = new HashMap<String,Object>();
		if(sysuser.getLoginName()==null || sysuser.getLoginName().trim().equals("")){
			json = this.setJson(false, "请输入登录名", null);
			return json;
		}else{
			if(!WebUtils.checkLoginName(sysuser.getLoginName().trim())){
				json = this.setJson(false, "请输入6到20位字母或者和数字组合的登录名", null);
				return json;
			}
		}
		if(sysuser.getLoginPwd()==null || !WebUtils.isPasswordAvailable(sysuser.getLoginPwd())){
			json = this.setJson(false, "密码错误，密码可由“_”，数字，英文大于等6位小于等于16位", null);
			return json;
		}
		if(sysuser.getEmail()!=null && !sysuser.getEmail().trim().equals("") && !WebUtils.checkEmail(sysuser.getEmail(), 50)){
			json = this.setJson(false, "请输入正确的邮箱号", null);
			return json;
		}
		if(sysuser.getTel()!=null && !sysuser.getTel().trim().equals("") && !WebUtils.checkMobile(sysuser.getTel())){
			json = this.setJson(false, "请输入正确的电话号码", null);
			return json;
		}
		return null;
	}
	
	/**
	 * 分页查询后台用户列表
	 */
	@RequestMapping("/userlist")
	public ModelAndView querySysUserList(HttpServletRequest request,@ModelAttribute("querySysUser") QuerySysUser querySysUser,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			//设置页面路径
			model.setViewName(getViewPath("/admin/system/sys-user-list"));
			//查询用户数据
			List<SysUser> sysUserList = sysUserService.querySysUserPage(querySysUser, page);
			//向页面传数据
			model.addObject("userList", sysUserList);
			model.addObject("page",page);
			//查询所有的角色
			List<SysRole> sysRoleList = sysRoleService.queryAllRoleList();
			model.addObject("sysRoleList",sysRoleList);
		}catch (Exception e) {
			logger.error("querySysUserList()--error",e);
			model.setViewName(this.setExceptionRequest(request, e));
		}
		return model;
	}
	/**
	 * 更新当前登录后台用户的密码页面
	 */
	@RequestMapping("/user/toupdatepwd")
	public String toUpdateUserPwd(HttpServletRequest request){
		return getViewPath("/admin/system/update_user_pwd");
	}
	/**
	 * 更新当前登录后台用户的密码页面
	 */
	@RequestMapping("/user/updatepwd")
	@ResponseBody
	public Map<String,Object> updateUserPwd(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			//获得密码参数
			String oldPwd = request.getParameter("oldPwd");
			String newPwd = request.getParameter("newPwd");
			String confirmPwd = request.getParameter("confirmPwd");
			//验证密码格式
			String falg = validatePwdParameter(oldPwd,newPwd,confirmPwd,SingletonLoginUtils.getLoginSysUserId(request));
			if(!"true".equals(falg)){
				json = this.setJson(false, "false", falg);
				return json;
			}
			//更新密码
			SysUser sysuser = new SysUser();
			sysuser.setUserId(SingletonLoginUtils.getLoginSysUserId(request));
			sysuser.setLoginPwd(MD5.getMD5(newPwd));


			sysUserService.updateUserPwd(sysuser);
			json = this.setJson(true, "true", "true");
		}catch (Exception e) {
			logger.error("updateUserPwd()--error",e);
		}
		return json;
	}
	/**
	 * 判断修改密码参数是否正确
	 *
	 */
	String validatePwdParameter(String oldPwd,String newPwd,String confirmPwd,int sysUserId){
		//旧密码为空
		if(StringUtils.isEmpty(oldPwd)){
			return "oldPwdisnull";
		}
		//新密码为空
		if(StringUtils.isEmpty(newPwd)){
			return "newPwdisnull";
		}
		//确认密码为空
		if(StringUtils.isEmpty(confirmPwd)){
			return "confirmPwdisnull";
		}
		//密码格式验证
		if(!WebUtils.isPasswordAvailable(newPwd)){
			return "newPwderror";
		}
		//确认密码和新密码不同
		if(!newPwd.equals(confirmPwd)){
			return "newPwdNotEqualsconfirmPwd";
		}
		//新密码为空
		if(oldPwd.equals(newPwd)){
			return "newPwdEquestOldPwd";
		}
		//旧密码不正确
		SysUser u = sysUserService.querySysUserByUserId(sysUserId);
		if(!u.getLoginPwd().equals(MD5.getMD5(oldPwd))){
			return "oldPwdIsError";
		}
		return "true";
	}
}
