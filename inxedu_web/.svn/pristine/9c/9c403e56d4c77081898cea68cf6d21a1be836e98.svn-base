package com.inxedu.os.edu.controller.user;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/user/ajax")
public class UserAjaxController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserAjaxController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired(required=false)
	private MemberRecordService memberRecordService;

	@InitBinder({"user"})
	public void initBinderUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}
	
	@InitBinder("msgReceive")
	public void initBinderMsgReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("msgReceive.");
	}
	
	/**
	 * 获取登录
	 */
	@RequestMapping("/getloginUser")
	@ResponseBody
	public Map<String,Object> getLoginUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			User user = SingletonLoginUtils.getLoginUser(request);
			if(user==null|| user.getUserId()==0){
				json = this.setJson(false, null, null);
			}else{
				json = this.setJson(true, null, user);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("getLoginUser()---error",e);
		}
		return json;
	}
	/**
	 * 获取登录
	 */
	@RequestMapping("/getVipInfo")
	@ResponseBody
	public Map<String,Object> getVipInfo(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(ObjectUtils.isNotNull(memberRecordService)){
					/*查询所有会员类型当前时间的开通情况*/
				List<MemberRecordDTO> memberRecordDTOList = memberRecordService.userMemberInfo(new Long(SingletonLoginUtils.getLoginUserId(request)));
				json = this.setJson(true,"",memberRecordDTOList);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("getVipInfo()---error",e);
		}
		return json;
	}
	/**
	 * 执行登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String ipForget = request.getParameter("ipForget");
			if(!StringUtils.isNotEmpty(account)){
				json = this.setJson(false, "请输入账户/邮箱/手机号", null);
				return json;
			}
			if(!StringUtils.isNotEmpty(password)){
				json = this.setJson(false, "请输入登录密码", null);
				return json;
			}
			User user = userService.getLoginUser(account, MD5.getMD5(password));
			if(user==null){
				//验证账号为邮箱格式登陆时返回下面提示
				if(StringUtils.validEmail(account)){
					json = this.setJson(false, "邮箱或密码错误", null);
					return json;
				}
				//验证账号为手机格式登陆时返回下面提示
				if(StringUtils.isMobileNo(account)){
					json = this.setJson(false, "手机号或密码错误", null);
					return json;
				}
				//验证账号为账号格式登陆时返回下面提示
				json = this.setJson(false, "账号或密码错误", null);
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
	
	/**
	 * 创建学员
	 */
	@RequestMapping("/createuser")
	@ResponseBody
	public Map<String,Object> createUser(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> map = new HashMap<String, Object>();

			HttpSession session = request.getSession();
			//从缓存提取邮箱验证码
			String emailCodeNum = (String) CacheUtil.get(session.getId()+"_emailCodeNum");
			//从缓存提取手机验证码
			String mobileCodeNum = (String) CacheUtil.get(session.getId()+"_mobileCodeNum");
			//获得输入的验证码
			String registerCode = request.getParameter("registerCode")==null?"":request.getParameter("registerCode");
			//获得输入的手机验证码
			String mobileCode = request.getParameter("mobileCode")==null?"":request.getParameter("mobileCode");
			//获得输入的邮箱验证码
			String emailCode = request.getParameter("emailCode")==null?"":request.getParameter("emailCode");
			//获得验证码
			Object randCode = request.getSession().getAttribute(CommonConstants.RAND_CODE);
			//注册开关配置
			Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());

			//当关闭邮箱和手机验证码时检验原始验证码是否正确
			//检验手机验证码是否正确
			if ("ON".equals(registerController.get("phoneProving"))){
				if (!mobileCode.equals(mobileCodeNum)||mobileCodeNum==null){
					map.put("success",false);
					map.put("message","请输入正确的手机验证码");
					return map;
				}
			}
			if ("OFF".equals(registerController.get("phoneProving"))){
				if (ObjectUtils.isNull(randCode) || !randCode.equals(registerCode)){
					map.put("success",false);
					map.put("message","请输入正确的验证码");
					return map;
				}
			}
			user.setRegisterFrom(UserRegisterFrom.register.toString());
			json = userService.createDoRegister(request,response,user);
		}catch (Exception e) {
			logger.error("createUser()--eror",e);
		}
		return json;
	}
    
    /**
     * 查询该用户有多少未读消息
     */
    @RequestMapping(value = "/queryUnReadLetter")
    @ResponseBody
    public Map<String, Object> queryUnReadLetter(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
			int userId=SingletonLoginUtils.getLoginUserId(request);
            if (userId== 0) {
                return map;
            }
			MsgReceive msgReceive = new MsgReceive();
			msgReceive.setReceivingCusId(Long.valueOf(userId));// set用户id
			msgReceive.setStatus(-1);
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveList(msgReceive);// 查询站内信收件箱
            map.put("entity", queryLetterList);// 把值放入map中返回json
        } catch (Exception e) {
            logger.error("UserController.queryUnReadLetter()", e);
            setExceptionRequest(request, e);
        }
        return map;
    }


}