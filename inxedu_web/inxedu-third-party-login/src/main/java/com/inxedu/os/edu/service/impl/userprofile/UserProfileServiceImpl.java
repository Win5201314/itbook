package com.inxedu.os.edu.service.impl.userprofile;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.dao.userprofile.UserProfileDao;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.userprofile.UserProfileService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.inxedu.os.common.controller.BaseController.getViewPath;


/**
 * UserProfile管理接口
 * @author www.inxedu.com
 */
@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private UserService userService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired(required=false)
    private InvitationRecordService invitationRecordService;

    /**
     * 添加UserProfile
     * 
     * @param userProfile
     *            要添加的UserProfile
     * @return id
     */
    public Long addUserProfile(UserProfile userProfile) {
        return userProfileDao.addUserProfile(userProfile);
    }

    /**
     * 根据id删除一个UserProfile
     * 
     * @param id
     *            要删除的id
     */
    public void deleteUserProfileById(int id) {
        userProfileDao.deleteUserProfileById(id);
    }

    /**
     * 修改UserProfile
     * 
     * @param userProfile
     *            要修改的UserProfile
     */
    public void updateUserProfile(UserProfile userProfile) {
        userProfileDao.updateUserProfile(userProfile);
    }

    /**
     * 根据userId获取单个UserProfile对象
     * 
     * @param userId
     *            要查询的userId
     * @return List<UserProfile>
     */
    public List<UserProfile> getUserProfileByUserId(int userId) {
        return userProfileDao.getUserProfileByUserId(userId);
    }

    /**
     * 根据条件获取UserProfile列表
     * 
     * @param userProfile
     *            查询条件
     * @return List<UserProfile>
     */
    public List<UserProfile> getUserProfileList(UserProfile userProfile) {
        return userProfileDao.getUserProfileList(userProfile);
    }

    /**
	 * 第三方注册
	 */
	@SuppressWarnings("unchecked")
	public User addOpenAppRegister(HttpServletRequest request, HttpServletResponse response, UserProfile userProfile) throws Exception {
		//如果当前登陆了则返回当前登陆用户id 如果没有登陆则创建新用户返回id
		User user = queryIsLoginUserOrCreateUser(userProfile.getAvatar(),userProfile.getName(),request);
		if(ObjectUtils.isNotNull(user)&&user.getUserId()!=0){
			userProfile.setProfiledate(new Date());
			userProfile.setUserid(Long.valueOf(user.getUserId()));
			addUserProfile(userProfile);
			// 注册时发送系统消息
			Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
			Map<String,Object> web=(Map<String,Object>)websitemap.get("web");
			String company=web.get("company").toString();
			msgReceiveService.sendMessage(user.getUserId(), "登录提示","register", true, company);
		}
		// 执行登录操作
		userService.queryDoLogin(user,"true",response,request);
		return user;
	}
	//如果当前登陆了则返回当前登陆用户id 如果没有登陆则创建新用户返回id
	public User queryIsLoginUserOrCreateUser(String photo, String cusName,HttpServletRequest request)throws Exception{

		User user = SingletonLoginUtils.getLoginUser(request);
		System.out.println("判断当前是否登陆"+user);
		if(ObjectUtils.isNotNull(user)){
			return user;
		}
		//创建一个用户用于第三方账户的绑定
		String pwd=genericRandomPwd();
		user = new User();
		user.setPassword(pwd);
		user.setShowName(cusName);
		user.setCreateTime(new Date());
		user.setPicImg(photo);
		user.setRegisterFrom(UserRegisterFrom.OpenAppRegisterFrom.toString());//用户注册来源 第三方登陆用户
		user.setIsavalible(1);
		//生成邮箱
		userService.createUser(user);

		//邀请码
		String invitationCodeStr=WebUtils.getCookie(request, "invitationCode");
		if(StringUtils.isNotEmpty(invitationCodeStr)){
			//查询邀请码对应的用户是否存在
			QueryUser queryUser=new QueryUser();
			queryUser.setInvitationCode(invitationCodeStr);
			List<User> userList=userService.queryUserList(queryUser);
			if(ObjectUtils.isNotNull(userList)){
				//添加邀请记录 ,给邀请人返现
				InvitationRecord invitationCode=new InvitationRecord();
				invitationCode.setUserId(Long.valueOf(userList.get(0).getUserId()));
				invitationCode.setInvitationUserId(Long.valueOf(user.getUserId()));
				invitationRecordService.addInvitationRecord(invitationCode);
			}
		}
		return user;
	}

	@Override
	public UserProfile queryUserProfileById(Long id) {
		return userProfileDao.queryUserProfileById(id);
	}

	@Override
	public int queryUserProfileConflict(UserProfile userProfile) {
		return userProfileDao.queryUserProfileConflict(userProfile);
	}

	/**
	 * 生成随机密码
	 * @return
	 */
	public String genericRandomPwd() {
		StringBuffer ranPwd = new StringBuffer("");
		Random ran = new Random();
		int location = 0;
		for (int i = 0; i < 9; i++) {
			location = ran.nextInt(10);
			ranPwd.append(location);
		}
		return ranPwd.toString();
	}

	/**
	 * 如果没登陆过则执行绑定操作  如果登陆过执行登陆操作
	 */
	public String queryDoUserThreeLogin(UserProfile userProfile,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		//查询是否绑定过
		List<UserProfile> list = getUserProfileList(userProfile);
		UserProfile queryUserProfile=null;
		if (ObjectUtils.isNotNull(list)) {
			queryUserProfile = list.get(0);
		}
		//如果已经绑定过账号则执行登陆方法
		if (ObjectUtils.isNotNull(queryUserProfile) && ObjectUtils.isNotNull(queryUserProfile.getUserid()) && queryUserProfile.getUserid().intValue() > 0) {//已经绑定过存在的帐号
			//如果 已存在的valueTwo值空，新valueTwo的值不为空 则修改
			if(StringUtils.isNotEmpty(userProfile.getValueTwo()) && StringUtils.isEmpty(queryUserProfile.getValueTwo())){
				queryUserProfile.setValueTwo(userProfile.getValueTwo());
				updateUserProfile(queryUserProfile);
			}

			// 登录操作
			Long cusId = queryUserProfile.getUserid();
			logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + queryUserProfile.getValue());
			//执行登陆操作
			User user = userService.queryUserById(Integer.parseInt(cusId+""));
			if(user.getIsavalible()==2){
				request.setAttribute("msg", "帐号已被禁用！");
				return getViewPath("/web/pay/visit-limit");
			}
			userService.queryDoLogin(user,"true",response,request);
			return  "redirect:/uc/index";

		}

		userProfile.setProfiledate(new Date());
		//第三方登录用户名为空时 默认：用户
		userProfile.setName(StringUtils.isNotEmpty(userProfile.getName()) ? userProfile.getName() : "用户");
		//下载图片
		userProfile.setAvatar(this.download(userProfile.getAvatar()));

		//获得 第三方登录绑定开关 配置
		Map<String,Object> webSwitch=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("serviceSwitch").get("serviceSwitch");
		//未绑定的则执行绑定流程
		String thirdLoginBinding=(String) webSwitch.get("thirdLoginBinding");
		//绑定的账号  自动生成还是进行对网站账号进行注册或绑定
		if ("ON".equals(thirdLoginBinding)){
			//如果已经有数据
			if (ObjectUtils.isNotNull(queryUserProfile) && queryUserProfile.getUserid().intValue() == 0) {
				//未绑定用户 更新库中 userProfile
				userProfile.setId(queryUserProfile.getId());
				userProfile.setUserid(queryUserProfile.getUserid());
				updateUserProfile(userProfile);
			} else {
				//添加第三方登录信息
				userProfile.setUserid(0L);
				addUserProfile(userProfile);
			}
			//跳转关联页面
			return resultUrl(request, response, userProfile, redirectAttributes);
		}else{
			// 自动生成邮箱帐号
			addOpenAppRegister(request, response, userProfile);
			return  "redirect:/uc/index";
		}
	}

	/**
	 * 下载图片
	 */
	public String download(String urlString) throws Exception {
		Map map = new HashMap();
		map.put("imageUrl", urlString);
		map.put("param", "customer");
		String result=HttpUtil.doPost(CommonConstants.uploadServer + "/image/download", map);
		Map<String,String> resultMap=FastJsonUtil.json2StringMap(result);
		if(Boolean.valueOf(resultMap.get("success"))){
			return resultMap.get("entity").toString();
		}else{
			return "";
		}
	}

	/**
	 * 处理返回路径
	 *
	 * @param request
	 * @param response
	 * @param userProfile
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	private String resultUrl(HttpServletRequest request, HttpServletResponse response,
							 UserProfile userProfile, RedirectAttributes redirectAttributes) throws Exception {
		//获取分享人id存储获取后删除cookies
		String shareUser = WebUtils.getCookie(request, "shareUser");
		WebUtils.deleteCookie(request, response, "shareUser");

		//如果登录则绑定 已经登录的用户
		if (SingletonLoginUtils.getLoginUserId(request)>0) {
			userProfile.setUserid(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));

			this.updateUserProfile(userProfile);
			//redirectAttributes.addAttribute("msg", "绑定成功");
			return "redirect:/uc/index";
		} else {
			//用户未登录将进行账号绑定操作
			//跳转到 绑定页面 并传参
			Map<String, Object> map = new HashMap<>();
			map.put("name", userProfile.getName());
			map.put("avatar", userProfile.getAvatar());
			map.put("profiletype", userProfile.getProfiletype());
			map.put("value", userProfile.getValue());
			map.put("userProfileId", userProfile.getId());

			map.put("shareUser", shareUser);
			redirectAttributes.addAttribute("userProfileJson", FastJsonUtil.obj2JsonString(map));
			return "redirect:/front/bundingProfile";
		}
	}
}