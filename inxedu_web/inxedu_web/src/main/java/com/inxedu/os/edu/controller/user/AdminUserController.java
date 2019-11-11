package com.inxedu.os.edu.controller.user;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.letter.MsgSystem;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.letter.MsgSystemService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.inxedu.os.common.util.WebUtils.checkUsername;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserLoginLogService userLoginLogService;
	@Autowired
	private MsgSystemService msgSystemService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private OrderService orderService;
	@Autowired(required=false)
	private MemberRecordService memberRecordService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired(required=false)
	private MemberSaleService memberSaleService;
	@InitBinder({"user"})
	public void initUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("user.");
	}
	@InitBinder({"queryUser"})
	public void initQueryUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("queryUser.");
	}
	
	/**
	 * 查询用户登录目录
	 * @param request
	 * @param userId 用户ID
	 * @param page 分页条件
	 * @return
	 */
	@RequestMapping("/lookuserlog/{userId}")
	public ModelAndView lookUserLog(HttpServletRequest request,@PathVariable("userId") int userId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/user/user-loginlog"));
			page.setPageSize(10);
			List<UserLoginLog> logList = userLoginLogService.queryUserLogPage(userId, page);
			model.addObject("logList", logList);
			model.addObject("page", page);
			model.addObject("userId", userId);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("lookLog()---error",e);
		}
		return model;
	}
	
	/**
	 * 启用或禁用学员帐号
	 * @return Map<String,Object>
	 */
	@RequestMapping("/updateuserstate")
	@ResponseBody
	@SystemLog(type="update",operation="启用或禁用学员帐号")
	public Map<String,Object> updateState(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			userService.updateUserStates(user);
			//冻结用户
			if(user.getIsavalible()==2){
				//清除前台登录用户 缓存
				CacheUtil.remove(CacheUtil.get(CacheConstans.WEB_USER_LOGIN_PREFIX+user.getUserId())+"");
			}
			json = this.setJson(true, null, null);

		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateState()--error",e);
		}
		return json;
	}

	/**
	 * 分页查询学员列表
	 */
	@RequestMapping("/getuserList")
	public ModelAndView queryUserList(HttpServletRequest request,@ModelAttribute("queryUser")QueryUser queryUser,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/user/user-list"));//学员列表页面
			page.setPageSize(10);
			List<User> userList = userService.queryUserListPage(queryUser, page);
			model.addObject("userList", userList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryUserList()--error",e);
		}
		return model;
	}
	/**
	 * 用户修改
	 */
	@RequestMapping("/toupdateuser")
	public ModelAndView toupdateuser(HttpServletRequest request,@RequestParam int userId){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/user/update_user"));//学员列表页面
			User user = userService.queryUserById(userId);
			model.addObject("user", user);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("toupdateuser()--error",e);
		}
		return model;
	}
	/**
	 * 用户修改
	 */
	@RequestMapping("/updateuser")
	@ResponseBody
	@SystemLog(type="update",operation="修改用户信息")
	public Map<String, Object> updateuser(HttpServletRequest request,@ModelAttribute("user")User user){
		Map json = new HashMap();
		ModelAndView model = new ModelAndView();
		try{
			User u = userService.queryUserById(user.getUserId());
			if(ObjectUtils.isNull(user)){
				json = setJson(false,"","");
				return json;
			}
			if( StringUtils.isNotEmpty(user.getEmail())&&!u.getEmail().equals(user.getEmail())){
				if(userService.checkEmail(user.getEmail().trim())){
					json = this.setJson(false, "该邮箱已被使用", null);
					return json;
				}else{
					user.setEmail(user.getEmail());
				}
			}
			if(StringUtils.isNotEmpty(user.getMobile())&&!u.getMobile().equals(user.getMobile())){
				if(userService.checkMobile(user.getMobile().trim())){
					json = this.setJson(false, "该手机号已被使用", null);
					return json;
				}else{
					user.setMobile(user.getMobile());
				}
			}
			if(StringUtils.isNotEmpty(user.getUserName())&&!u.getUserName().equals(user.getUserName())){
				if(userService.checkUsername(user.getUserName().trim())){
					json = this.setJson(false, "该账号已被使用", null);
					return json;
				}else{
					user.setUserName(user.getUserName());
				}
			}
			//密码不为空则执行修改密码
			if(StringUtils.isNotEmpty(user.getPassword())){
				if(!WebUtils.isPasswordAvailable(user.getPassword())){
					json = this.setJson(false, "密码由字母和数字组成且≥6位≤16位", null);
					return json;
				}
				String password = request.getParameter("passwords")==null?"":request.getParameter("passwords");
				if(!user.getPassword().equals(password)){
					json = this.setJson(false, "两次密码不一至！", null);
					return json;
				}
				u.setPassword(MD5.getMD5(user.getPassword()));
				userService.updateUserPwd(u);
			}
			userService.updateUserAll(user);
			json = this.setJson(true, "成功", null);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryuser()--error",e);
		}
		return json;
	}

	
	/**
     * 跳转到发消息页面
     */
    @RequestMapping(value = "/letter/toSendSystemMessages")
    public ModelAndView senSystemMessages(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(getViewPath("/admin/user/to_send_systemMessage"));
        return modelAndView;
    }
    
    /**
     * 跳转到 批量  发消息页面
     */
    @RequestMapping(value = "/letter/toSendSystemMessagesBatch")
    public ModelAndView senSystemMessagesBatch(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(getViewPath("/admin/user/to_send_systemMessage_batch"));
        return modelAndView;
    }
    
    /**
     * 发送系统消息
     */
    @RequestMapping(value = "/letter/sendJoinGroup")
    @ResponseBody
	@SystemLog(type = "add",operation = "发送系统消息")
    public Map<String, Object> sendSystemInform(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String content = request.getParameter("content");// 发送系统消息的内容
            MsgSystem msgReceive = new MsgSystem();
            msgReceive.setContent(content);// 添加站内信的内容
            msgReceive.setUpdateTime(new Date());// 更新时间s
            msgReceive.setAddTime(new Date());// 添加时间
            msgSystemService.addMsgSystem(msgReceive);
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("AdminUserController.sendSystemInform", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
    
    /**
     * 批量发送系统消息
     */
    @RequestMapping(value = "/letter/sendJoinGroupBatch")
	@SystemLog(type = "add",operation = "批量发送系统消息")
	@ResponseBody
    public Map<String, Object> sendJoinGroupBatch(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
        	String userEmails=request.getParameter("userEmails");// 发送系统消息的用户邮箱
        	String content = request.getParameter("content");// 发送系统消息的内容
        	if (ObjectUtils.isNull(userEmails)) {
        		map.put("message", "用户邮箱不能为空");
        		return map;
			}
        	String userEmailsArr[]=userEmails.split(",");
        	for(int i=0;i<userEmailsArr.length;i++){
        		if(ObjectUtils.isNotNull(userEmailsArr[i].trim())){
        			User user=userService.queryUserByEmailOrMobile(userEmailsArr[i].trim());
            		if (ObjectUtils.isNotNull(user)) {
            			msgReceiveService.addSystemMessageByCusId(content,Long.valueOf(user.getUserId()),"");
    				}
        		}
        	}
        	MsgSystem msgSystem = new MsgSystem();
			msgSystem.setAddTime(new Date());
			msgSystem.setUpdateTime(new Date());
			msgSystem.setContent(content);
        	msgSystemService.addMsgSystem(msgSystem);
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("AdminUserController.sendJoinGroupBatch", e);
            setExceptionRequest(request, e);
            map.put("message", "系统错误,请稍后重试");
        }
        return map;
    }
    
    /**
	 * 用户导出
	 */
	@RequestMapping("/export")
	public void userListExport(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("queryUser")QueryUser queryUser) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/")+"/excelfile/user";
			//文件名
			String expName = "学员信息_" + DateUtils.getStringDateShort();
			//表头信息
	        String[] headName = { "ID","邮箱","手机","注册来源","用户名","昵称","性别","年龄", "注册时间","状态"};

	        //拆分为一万条数据每Excel，防止内存使用太大
			PageEntity page=new PageEntity();
			page.setPageSize(10000);
			userService.queryUserListPage(queryUser, page);
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<User> userList = userService.queryUserListPage(queryUser, page);
				List<List<String>> list=userJoint(userList);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
	        FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 学员信息excel格式拼接
	 */
	public List<List<String>> userJoint(List<User> userList){
		List<List<String>> list = new ArrayList<List<String>>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < userList.size(); i++) {
			List<String> small = new ArrayList<String>();
			small.add(userList.get(i).getUserId() + "");
			small.add(userList.get(i).getEmail());
			small.add(userList.get(i).getMobile());
			if (UserRegisterFrom.register.toString().equals(userList.get(i).getRegisterFrom())) {
				small.add("网站");
			} else if (UserRegisterFrom.admin.toString().equals(userList.get(i).getRegisterFrom())) {
				small.add("后台批量开通");
			} else if (UserRegisterFrom.userCard.toString().equals(userList.get(i).getRegisterFrom())) {
				small.add("学员卡");
			} else if (UserRegisterFrom.OpenAppRegisterFrom.toString().equals(userList.get(i).getRegisterFrom())) {
				small.add("第三方登录");
			} else {
				small.add("");
			}
			small.add(userList.get(i).getUserName());
			small.add(userList.get(i).getShowName());
			if (userList.get(i).getSex() == 0) {
				small.add("--");
			} else if (userList.get(i).getSex() == 1) {
				small.add("男");
			} else {
				small.add("女");
			}
			small.add(String.valueOf(userList.get(i).getAge()));
			small.add(ObjectUtils.isNotNull(userList.get(i).getCreateTime())?format.format(userList.get(i).getCreateTime()) : "");
			if (userList.get(i).getIsavalible() == 1) {
				small.add("正常");
			} else if (userList.get(i).getIsavalible() == 2) {
				small.add("冻结");
			}
			list.add(small);
		}
		return list;
	}
	
	// 批量开通
	@RequestMapping("/toBatchOpen")
	public String toBatchOpen() {
		return getViewPath("/admin/user/batchOpen");
	}
	
	/**
	 * 批量开通学员账号
	 */
	@RequestMapping("/importExcel")
	@SystemLog(type = "add",operation = "批量开通学员账号")
	public ModelAndView importProcess(HttpServletRequest request, @RequestParam("myFile") MultipartFile myfile,@RequestParam("mark") Integer mark) {
		ModelAndView modelandView = new ModelAndView();
		try {
			logger.info("myFile:" + myfile.getName());
			String msg = userService.updateImportExcel(request,myfile,mark);
			request.setAttribute("msg", msg);
			if(msg==null||msg.equals("")){
				modelandView.setViewName("/common/success");
			}else{
				modelandView.setViewName("/common/msg_error");
			}
		} catch (BaseException e) {
            logger.error("AdminUserController.importExcel", e);
            request.setAttribute("msg", e.getMessage());
            return new ModelAndView("/common/msg_error");
        } catch (Exception e) {
        	logger.error("AdminUserController.importExcel", e);
        	request.setAttribute("msg", e.getMessage());
        	return new ModelAndView("/common/msg_error");
		}
		return modelandView;
	}

	/**
	 * 单个开通学员账号
	 */
	@RequestMapping("/singleCreateUser")
	@ResponseBody
	@SystemLog(type="add",operation="单个开通学员账号")
	public Map<String, Object> singleCreateUser(HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			String userName = request.getParameter("userName");//账户
			String pwd = request.getParameter("password");//密码
			String email= request.getParameter("email");//邮箱
			String mobile = request.getParameter("mobile");//手机

			//账户
			if(StringUtils.isEmpty(userName)){
				json = this.setJson(false, "账户不能为空", null);
				return json;
			}
			boolean b = userService.checkUsername(userName);
			if (b==true) {
				json = this.setJson(false, "账户已存在", null);
				return json;
			}
			if (checkUsername(userName)==false) {
				json = this.setJson(false, "账户格式错误", null);
				return json;
			}
			//密码
			if(StringUtils.isNotEmpty(pwd)){
				if(!WebUtils.isPasswordAvailable(pwd)){
					json = this.setJson(false, "密码格式错误", null);
					return json;
				}
			}
			if(StringUtils.isEmpty(pwd)){
				pwd="123456";
			}
			//邮箱
			if(StringUtils.isNotEmpty(email)){
				if (WebUtils.checkEmail(email, 50)==false) {
					json = this.setJson(false, "邮箱格式错误", null);
					return json;
				}
				b = userService.checkEmail(email.toLowerCase());
				if (b==true) {
					json = this.setJson(false, "邮箱已存在", null);
					return json;
				}
			}
			//手机
			if(StringUtils.isNotEmpty(mobile)){
				if(!WebUtils.checkMobile(mobile)){
					json = this.setJson(false, "手机格式错误", null);
					return json;
				}
				b = userService.checkMobile(mobile);
				if (b==true) {
					json = this.setJson(false, "手机已存在", null);
					return json;
				}
			}

			User user = new User();
			user.setUserName(userName);//用户学员账户
			user.setEmail(email);//用户学员邮箱
			user.setMobile(mobile);//用户学员手机
			user.setPassword(MD5.getMD5(pwd));//用户学员密码
			user.setCreateTime(new Date());//注册时间
			user.setLastSystemTime(new Date());//上传统计系统消息时间
			user.setRegisterFrom(UserRegisterFrom.admin.toString());//用户注册来源 后台批量开通用户
			user.setIsavalible(1);
			userService.createUser(user);//添加学员
			json = this.setJson(true, "", null);
		} catch (Exception e) {
			e.printStackTrace();
			json = this.setJson(false, "系统异常", null);
		}
		return json;
	}

	/**
	 * 赠送课程
	 */
	@RequestMapping("/freeCourse")
	@ResponseBody
	@SystemLog(type="add",operation="赠送课程")
	public Map<String, Object> toFreeCourse(HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int courseId = Integer.parseInt(request.getParameter("courseId"));

			// 判断课程是否已过期
			boolean overdue = false;
			Course course = courseService.queryCourseById(courseId);
			if (ObjectUtils.isNull(course)) {
				json = this.setJson(false, "此课程不存在", null);
				return json;
			}
			//如果课程价格为0可以观看
			if(course.getCurrentPrice().compareTo(new BigDecimal(0))==0){
				json = this.setJson(false, "赠送课程价格不能为零", null);
				return  json;
			}
			//判断该课程是否可以观看
			boolean isok=orderService.checkUserCursePay(userId, courseId);
			if(isok){
				json = this.setJson(false, "用户已经购买了此课程", null);
				return json;
			}
			if (course.getLoseType() == 0) {
				Date date = new Date();// 今天
				String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
				String authStr = DateUtils.formatDate(course.getEndTime(), "yyyy-MM-dd");
				if (Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1 > 0) {
					overdue = true;
				}
			} else {
				overdue = true;
			}
			if (overdue) {
				orderService.addOrderMsg(userId, courseId);
				json = this.setJson(true, "赠送成功", null);
				//发送站内信
				String conent = "【<a class=\"course-title c-333\" target=\"_blank\" href=\""+ CommonConstants.contextPath+"/front/couinfo/"+courseId+"\">"+course.getCourseName()+"</a>】";
				msgReceiveService.sendMessage( userId, "赠送课程提示",MsgType.giveCourse.toString(), true, conent);
			} else {
				json = this.setJson(false, "该课程已过期", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json = this.setJson(false, "系统异常", null);
		}
		return json;
	}
	/**
	 * 赠送课程
	 */
	@RequestMapping("/freeMember")
	@ResponseBody
	@SystemLog(type="add",operation="赠送会员")
	public Map<String, Object> freeMember(HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			Long userId = Long.parseLong(request.getParameter("userId"));
			Long memberSaleId = Long.parseLong(request.getParameter("memberSaleId"));

			/*会员商品*/
			MemberSale memberSale = memberSaleService.getMemberSaleById(memberSaleId);
			/*会员的开通情况*/
			List<MemberRecordDTO> memberRecordDTOs = memberRecordService.userMemberInfo(userId);
			TrxorderDetail trxorderDetail = null;
			for (int i=0;i<memberRecordDTOs.size();i++){
				/*如果开通过该会员 则为续费*/
				if(memberRecordDTOs.get(i).getMemberType()==memberSale.getType()){
					/*开通会员的流水记录*/
					trxorderDetail = trxorderDetailService.getTrxorderDetailById(memberRecordDTOs.get(i).getId());
				}
			}


			orderService.addFreeMember(userId,memberSaleId,trxorderDetail);
			json = this.setJson(true, "赠送成功", null);
			//发送站内信
			String conent = "【<a class=\"course-title c-333\" target=\"_blank\" href=\""+ CommonConstants.contextPath+"/uc/associator"+"\">"+memberSale.getName()+"</a>】";
			msgReceiveService.sendMessage( Integer.parseInt(userId+""), "赠送课程提示",MsgType.giveCourse.toString(), true, conent);

		} catch (Exception e) {
			e.printStackTrace();
			json = this.setJson(false, "系统异常", null);
		}
		return json;
	}
	
	/**
	 * 用户选择页（小页面弹出）
	 */
	@RequestMapping("/select_userlist/{type}")
	public ModelAndView selectUserList(@ModelAttribute User user, @ModelAttribute("page") PageEntity page, @PathVariable("type") int type) {
		ModelAndView modelandView = new ModelAndView();
		// 设置返回页面
		modelandView.setViewName(getViewPath("/admin/user/select_user_list"));
		try {
			// 查询学员列表
			/*如果是短信只查有手机号的用户*/
			if (type==1){
				user.setMobileNotNull("1");

			}
			/*如果是邮箱只查有邮箱的用户*/
			if (type==2){
				user.setEmailNotNull("@");

			}
			List<User> list = userService.getUserListPage(user, page);
			// 把参数放到modelAndView中
			modelandView.addObject("list", list);
			modelandView.addObject("page", page);
			modelandView.addObject("type", type);// 1 短信 2邮箱
		} catch (Exception e) {
			logger.error("AdminUserController.userList", e);
		}
		return modelandView;
	}
	
}
