package com.inxedu.os.app.controller.user;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryOrder;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.account.UserAccounthistoryService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.course.CourseFavoritesService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.system.SysUserLoginLogService;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/webapp")
public class AppUserController extends BaseController{
	private static Logger logger=Logger.getLogger(AppUserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SysUserLoginLogService sysUserLoginLogService;
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
	 * 注册
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
			user.setRegisterFrom(UserRegisterFrom.register.toString());
			json = userService.createDoRegister(request,response,user);

		}catch (Exception e) {
			json = this.setJson(false, "异常", null);
			logger.error("createUser()--eror",e);
		}
		return json;
	}
	
	/**
	 * 获得用户
	 */
	@RequestMapping("/queryUserById")
	@ResponseBody
	public Map<String, Object> queryUserById(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String id=request.getParameter("userId");
			if(id==null||id.equals("")){
				json = this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(id)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			User user = userService.queryUserById(Integer.parseInt(id));
			json=this.setJson(true, "获取用户成功", user);
		}catch(Exception e){
			json = this.setJson(false, "异常", null);
			logger.error("queryUserById()--eror",e);
		}
		return json;
	}

	/**
     * 收藏课程
     */
    @RequestMapping("/front/createfavorites")
    @ResponseBody
    public Map<String,Object> createFavorites(HttpServletRequest request){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		//用户Id
    		String userId = request.getParameter("userId");
    		if(userId==null||userId.trim().equals("")){
    			json = this.setJson(false, "用户Id不能为空", null);
    			return json;
    		}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
    		//课程Id
    		String courseId=request.getParameter("courseId");
    		if(courseId==null||courseId.trim().equals("")){
    			json = this.setJson(false, "课程Id不能为空", null);
    			return json;
    		}
    		//判断是否收藏过
    		boolean is = courseFavoritesService.checkFavorites(Integer.parseInt(userId), Integer.parseInt(courseId));
    		if(is){
    			json = this.setJson(false, "该课程你已经收藏过了！", null);
    			return json;
    		}
    		CourseFavorites courseFavorites=new CourseFavorites();
    		courseFavorites.setCourseId(Integer.parseInt(courseId));
    		courseFavorites.setUserId(Integer.parseInt(userId));
    		courseFavorites.setAddTime(new Date());
    		courseFavoritesService.createCourseFavorites(courseFavorites);
    		json = this.setJson(true, "收藏成功", null);
    	}catch (Exception e) {
    		json = this.setJson(false, "异常", null);
			logger.error("createFavorites()--error",e);
		}
    	return json;
    }
    
    /**
	 * 删除收藏
	 */
	@RequestMapping("/deleteFaveorite")
	@ResponseBody
	public Map<String, Object> deleteFavorite(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String courseId=request.getParameter("courseId");
			if(courseId==null||courseId.trim().equals("")){
				json=setJson(false, "课程id不能为空", null);
				return json;
			}
			CourseFavorites courseFavorites=new CourseFavorites();
			courseFavorites.setCourseIdStr(courseId);
			courseFavorites.setUserId(SingletonLoginUtils.getLoginUserId(request));
			courseFavoritesService.deleteCourseFavorites(courseFavorites);
			json=setJson(true, "取消收藏成功", null);
		}catch (Exception e) {
			json=setJson(false, "异常", null);
			logger.error("deleteFavorite()---error",e);
		}
		return json;
	}
	
	/**
	 * 我的课程
	 */
	@RequestMapping("/myCourse")
	@ResponseBody
	public Map<String, Object> myCourse(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String currentPage=request.getParameter("currentPage");//当前页
			if(currentPage==null||currentPage.trim().equals("")){
				json=this.setJson(false, "页码不能为空", null);
				return json;
			}
			page.setCurrentPage(Integer.parseInt(currentPage));
			
			page.setPageSize(10);//每页显示多少条数据
			String pageSize=request.getParameter("pageSize");
			if(pageSize!=null){
				page.setPageSize(Integer.parseInt(pageSize));
			}
			
			String userId=request.getParameter("userId");//用户Id
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			String type = request.getParameter("sellType");
			String ifOver = request.getParameter("ifOver");
			//获取登录用户ID
			QueryCourse queryCourse=new  QueryCourse();
			queryCourse.setUserId(Integer.parseInt(userId));

			if (StringUtils.isNotEmpty(type)){
				queryCourse.setSellType(type);
			}
			queryCourse.setIsoverdue("false");//默认查询没有过期的
			if ("true".equals(ifOver)){
				queryCourse.setIsoverdue("true");
			}
			List<CourseDto> courseList;
			if (!"PACKAGE".equals(type)&&StringUtils.isEmpty(ifOver)){
            	/*如果不是套餐和过期课程查询我的课程 */
				courseList =   courseService.querySelfCourse(queryCourse,page);
				courseList = WebUtils.pageList(courseList,page);
			}else {
				//如果是套餐  或 过期课程
				courseList = courseService.queryMyCourseList(queryCourse,page);
				/*若果是套餐课set子课list*/
				if ("PACKAGE".equals(type)&&(courseList!=null&&courseList.size()>0)){
					for (Course course:courseList){
						List<Long> cId = new ArrayList<>();
						cId.add(new Long(course.getCourseId()));
						course.setCourseList(courseService.getCourseListPackage(cId));
					}
				}
			}
			/*查询课程最近的一次 学习记录*/
			if (courseList!=null &&courseList.size()>0){
				for (Course course:courseList){
					course.setCourseStudyhistory(courseService.getCourseStudyhistory(request,new Long(course.getCourseId())));
				}
			}
			json=this.setJson(true, "查询成功--我的课程", courseList);
		}catch(Exception e){
			json=setJson(false, "异常", null);
			logger.error("myCourse()---error",e);
		}
		return json;
	}
	
	/**
	 * 我的收藏课程列表
	 */
	@RequestMapping("/myFavorites")
	@ResponseBody
	public Map<String, Object> myFavorites(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		Map<String, Object> json=new HashMap<String,Object>();
		try{
			String currentPage=request.getParameter("currentPage");//当前页
			if(currentPage==null||currentPage.trim().equals("")){
				json=this.setJson(false, "页码不能为空", null);
				return json;
			}
			page.setCurrentPage(Integer.parseInt(currentPage));
			
			page.setPageSize(10);//每页显示多少条数据
			String pageSize=request.getParameter("pageSize");
			if(pageSize!=null){
				page.setPageSize(Integer.parseInt(pageSize));
			}
			
			String userId=request.getParameter("userId");//用户Id
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			List<FavouriteCourseDTO> favoriteList = courseFavoritesService.queryFavoritesPage(Integer.parseInt(userId), page);
			json=this.setJson(true, "查询成功--我的收藏课程列表", favoriteList);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("myFavorites()---error",e);
		}
		return json;
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Map<String,Object> updateUserInfo(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String userId=request.getParameter("userId");//用户Id
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			User updUser=userService.queryUserById(Integer.parseInt(userId));

			if(ObjectUtils.isNotNull(updUser)){
				String userName=request.getParameter("userName");//姓名
				if(userName==null||userName.trim().equals("")){
					updUser.setUserName(userName);//姓名

				}
				String showName=request.getParameter("showName");//昵称
				if(showName==null||showName.trim().equals("")){
					updUser.setShowName(showName);//昵称
				}
				String sex=request.getParameter("sex");//性别 1男 2女
				userService.updateUser(updUser);//修改基本信息
				json = this.setJson(true, "修改成功", updUser);

			}

		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("updateUserInfo()---error",e);
		}
		return json;
	}

	@Autowired
	private  UserLoginLogService userLoginLogService;
	/**
	 * 查询用户的某日登陆数，用于因酷官网调用
	 */
	@RequestMapping("/queryUserLoginLog")
	@ResponseBody
	public Map<String,Object> queryUserLoginLog(HttpServletRequest request,UserLoginLog userLoginLog){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			List<UserLoginLog> userLoginLogList = userLoginLogService.queryUserLoginLog(userLoginLog);
			if(ObjectUtils.isNull(userLoginLogList)){
				json = setJson(true,"0","0");
				return json;
			}
			json = setJson(true,userLoginLogList.size()+"",userLoginLogList.size()+"");
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("queryUserLoginLog()---error",e);
		}
		return json;
	}
	/**
	 * 查询我的优惠券
	 */
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@RequestMapping("/myCouPon")
	@ResponseBody
	public Map<String,Object> myCouPon(HttpServletRequest request, UserLoginLog userLoginLog, @ModelAttribute("queryCouponCode") QueryCouponCode queryCouponCode, @ModelAttribute("page") PageEntity page){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> result = new HashedMap();
			String userId = request.getParameter("userId");
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			if("OFF".equals(CacheConstans.COUPON_IS_OPEN) || ObjectUtils.isNull(couponCodeService)){
				result.put("msg","您暂未购买【优惠券】功能，无法使用");
				return result;
			}
			//查询我的优惠券
			queryCouponCode.setUserId(Long.valueOf(userId));
			List<CouponCodeDTO> couponList = couponCodeService.queryCouponCodePageByUser(queryCouponCode, page);
			result.put("couponList",couponList);
			result.put("status", queryCouponCode.getStatus());
			json = setJson(true,"",result);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("myCouPon()---error",e);
		}
		return json;
	}
	/**
	 * 查询我的订单
	 */
	@Autowired(required = false)
	private MemberTypeService memberTypeService;
	@Autowired
	private OrderService orderService;
	@RequestMapping("/myOrderList")
	@ResponseBody
	public Map<String,Object> myOrderList(HttpServletRequest request, UserLoginLog userLoginLog, @ModelAttribute("queryOrder") QueryOrder queryOrder, @ModelAttribute("page") PageEntity page){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> result = new HashedMap();
			String state = request.getParameter("state");
			String userId = request.getParameter("userId");
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			queryOrder.setUserId(Integer.parseInt(userId));
			/*如果不是查看全部set查询条件*/
			if (!"ALL".equals(state)){
				queryOrder.setStates(state);
			}
			if (ObjectUtils.isNull(memberTypeService)){
				queryOrder.setCloseMember("TRUE");
			}
			List<Order> orderList = orderService.queryOrderForUc(queryOrder, page);
			// 订单信息
			result.put("orderList", orderList);
			result.put("page", page);
			result.put("state", state);
			json = setJson(true,"",result);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("myOrderList()---error",e);
		}
		return json;
	}
	@RequestMapping("/cancleoder")
	@ResponseBody
	public Map<String,Object> cancleoder(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String orderId = request.getParameter("orderId");
			Order trxorder = orderService.queryOrderById(Integer.parseInt(orderId));
			trxorder.setStates("CANCEL");
			orderService.updateOrderState(trxorder);
			/**coupon_frozen_temp**/
			//优惠券 解冻
			// 查询优惠券编码
			if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)){
				CouponCode couponCodeTemp=couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
				if(couponCodeTemp!=null  &&  couponCodeTemp.getStatus()==6){//冻结才修改
					couponCodeTemp.setStatus(1);//1未使用
					couponCodeTemp.setRequestId("");
					couponCodeTemp.setTrxorderId(0L);
					couponCodeService.updateCouponCode(couponCodeTemp);
				}
				/**coupon_frozen_temp**/
			}
			json = setJson(true,"",null);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("myOrderList()---error",e);
		}
		return json;
	}

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccounthistoryService userAccounthistoryService;
	/*我的账户*/
	@RequestMapping("/myAccount")
	@ResponseBody
	public Map<String,Object> myAccount(HttpServletRequest request, @ModelAttribute("queryUserAccounthistory")QueryUserAccounthistory queryUserAccounthistory, @ModelAttribute("page")PageEntity page){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> result = new HashedMap();
			String userId = request.getParameter("userId");
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			UserAccount userAccount = userAccountService.getUserAccountByUserId(Long.parseLong(userId));
			result.put("userAccount", userAccount);
			queryUserAccounthistory.setUserId(Long.parseLong(userId));
			List<UserAccounthistory> accList = userAccounthistoryService.getWebUserAccountHistroyListByCondition(queryUserAccounthistory, page);
			result.put("accList", accList);
			json = setJson(true,"",result);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("myAccount()---error",e);
		}
		return json;
	}
	/**
	 * 修改用户密码
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Map<String,Object> updatePwd(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			user = userService.queryUserById(user.getUserId());
			if(user!=null){
				//原密码
				String nowPassword = request.getParameter("nowPassword")==null?"":request.getParameter("nowPassword");
				//新密码
				String newPassword = request.getParameter("newPassword")==null?"":request.getParameter("newPassword");
				//确认密码
				String confirmPwd = request.getParameter("confirmPwd")==null?"":request.getParameter("confirmPwd");
				if(nowPassword.equals("")|| nowPassword.trim().length()==0){
					json = this.setJson(false, "请输入旧密码！", 1);
					return json;
				}
				if(!user.getPassword().equals(MD5.getMD5(nowPassword))){
					json = this.setJson(false, "旧密码不正确！", 1);
					return json;
				}
				if(newPassword.equals("") || newPassword.trim().length()==0){
					json = this.setJson(false, "请输入新密码！", 2);
					return json;
				}
				if(!WebUtils.isPasswordAvailable(newPassword)){
					json = this.setJson(false, "密码只能是数字字母组合且大于等于6位小于等于16位", 2);
					return json;
				}
				if(!newPassword.equals(confirmPwd)){
					json = this.setJson(false, "新密码和确认密码不一致！", 2);
					return json;
				}
				user.setPassword(MD5.getMD5(newPassword));
				userService.updateUserPwd(user);
				json = this.setJson(true, "修改成功", null);
				return json;
			}else {
				json = this.setJson(false, "没有该用户", null);
			}
			json = this.setJson(false, "修改失败", null);
		}catch (Exception e) {
			json=this.setAjaxException(json);
			logger.error("updatePwd()--error",e);
		}
		return json;
	}
	/**
	 * 消息中心
	 */
	@RequestMapping("/letter")
	@ResponseBody
	public Map<String,Object> queryUserLetter(HttpServletRequest request, @ModelAttribute("page")PageEntity page){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> result = new HashedMap();
			String userId = request.getParameter("userId");
			if(userId==null||userId.trim().equals("")){
				json=this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
			MsgReceive msgReceive = new MsgReceive();
			msgReceive.setReceivingCusId(Long.valueOf(userId));// set用户id
			// 更新所有收件箱为已读
			msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, page);// 查询站内信收件箱
			//修改用户消息数后  重新加入缓存
			userService.setLoginInfo(request,msgReceive.getReceivingCusId().intValue(),"false");

			result.put("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
			result.put("page", page);// 分页参数放入modelAndView中
			json = setJson(true,"",result);
		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("queryUserLetter()---error",e);
		}
		return json;
	}
	/**
	 * 删除站内信收件箱
	 */
	@RequestMapping(value = "/delLetterInbox")
	@ResponseBody
	public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String userId = request.getParameter("userId");
			if(userId==null||userId.trim().equals("")){
				map=this.setJson(false, "用户Id不能为空", null);
				return map;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				map=this.setJson(false, "没有该用户", null);
				return map;
			}
			msgReceive.setReceivingCusId(Long.parseLong(userId));// set 用户id
			Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
			if (num.intValue() == 1) {
				map.put("message", "success");// 成功
			} else {
				map.put("message", "false");// 失败
			}
		} catch (Exception e) {
			logger.error("UserController.delLetterInbox()", e);
			setExceptionRequest(request, e);
		}
		return map;
	}
}
