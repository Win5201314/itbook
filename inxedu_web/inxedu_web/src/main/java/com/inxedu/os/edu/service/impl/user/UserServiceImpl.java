package com.inxedu.os.edu.service.impl.user;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.enums.account.AccountStatus;
import com.inxedu.os.edu.dao.user.UserDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.card.CardCodeService;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import static com.inxedu.os.edu.constants.enums.WebSiteProfileType.web;

/**
 * @author www.inxedu.com
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired(required=false)
	private CardCodeService cardCodeService;
	@Autowired
	private UserLoginLogService userLoginLogService;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired(required=false)
	private UserAccountService userAccountService;
	@Autowired(required=false)
	private InvitationRecordService invitationRecordService;


	public int createUser(User user) {
		user.setInvitationCode(StringUtils.getRandomString(10));//设置邀请码
		int userId=userDao.createUser(user);
		// 添加账户
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId(Long.valueOf(userId));
		userAccount.setCreateTime(new Date());
		userAccount.setLastUpdateTime(new Date());
		userAccount.setBalance(new BigDecimal(0));
		userAccount.setForzenAmount(new BigDecimal(0));
		userAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		userAccount.setCashAmount(new BigDecimal(0));
		userAccount.setVmAmount(new BigDecimal(0));
		userAccount.setBackAmount(new BigDecimal(0));
		userAccount.setVersion(Long.valueOf(0));
		if (ObjectUtils.isNotNull(userAccountService)){
			userAccountService.addUserAccount(userAccount);
		}
		return userId;
	}
	/**
	 * 注册用户
	 */
	public Map<String,Object> createDoRegister(HttpServletRequest request, HttpServletResponse response, User user) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		//获取密码
		String confirmPwd = request.getParameter("confirmPwd");
		/*获取是邮箱注册还是账号注册*/
		String ifEmailReg = request.getParameter("ifEmailReg")==null?"":request.getParameter("ifEmailReg");
		Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
		if("ON".equals(registerController.get("phoneProving"))){
			//验证手机号格式是否正确
			if(user.getMobile()==null || user.getMobile().trim().length()==0 || !WebUtils.checkMobile(user.getMobile())){
				map.put("success",false);
				map.put("message","请输入正确的手机号");
				return map;
			}
			//查看手机号是否已注册
			if(checkMobile(user.getMobile())){
				map.put("success",false);
				map.put("message","该手机号已被使用");
				return map;
			}
		}
		if ("off".equals(ifEmailReg)){
			if (WebUtils.checkUsername(user.getEmail())){
				if (this.checkUsername(user.getEmail())){
					map.put("success",false);
					map.put("message","账号已被使用");
					return map;
				}
				user.setUserName(user.getEmail());
				user.setEmail("");
			}
		}else {
			if(checkEmail(user.getEmail())){
				map.put("success",false);
				map.put("message","该邮箱号已被使用");
				return map;
			}
		}
		//验证密码格式是否正确
		if(user.getPassword()==null || user.getPassword().trim().length()==0 || !WebUtils.isPasswordAvailable(user.getPassword())){
			map.put("success",false);
			map.put("message","密码有字母和数字组合且≥6位≤16位");
			return map;
		}
		if(!user.getPassword().equals(confirmPwd)){
			map.put("success",false);
			map.put("message","两次密码不一致");
			return map;
		}

		user.setCreateTime(new Date());
		user.setIsavalible(1);
		user.setPassword(MD5.getMD5(user.getPassword()));
		user.setSex(user.getSex());
		user.setMsgNum(0);
		user.setSysMsgNum(0);
		user.setLastSystemTime(new Date());
		createUser(user);
		request.getSession().removeAttribute(CommonConstants.RAND_CODE);

		//邀请码
		String invitationCodeStr=WebUtils.getCookie(request, "invitationCode");
		if(StringUtils.isNotEmpty(invitationCodeStr)){
			//查询邀请码对应的用户是否存在
			QueryUser queryUser=new QueryUser();
			queryUser.setInvitationCode(invitationCodeStr);
			List<User> userList=this.queryUserList(queryUser);
			if(ObjectUtils.isNotNull(userList)){
				//添加邀请记录 ,给邀请人返现
				InvitationRecord invitationCode=new InvitationRecord();
				invitationCode.setUserId(Long.valueOf(userList.get(0).getUserId()));
				invitationCode.setInvitationUserId(Long.valueOf(user.getUserId()));
				invitationRecordService.addInvitationRecord(invitationCode);
			}
		}
		// 注册时发送系统消息
		Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(web.toString());
		Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
		String company = web.get("company").toString();
		msgReceiveService.sendMessage(user.getUserId(), "注册提示", MsgType.register.toString(), true, company);

		String uuid = StringUtils.createUUID().replace("-", "");
		//当前时间戳
		Long currentTimestamp=System.currentTimeMillis();
		//缓存用户
		CacheUtil.set(uuid, user,CacheConstans.USER_TIME);
		//缓存用户的登录时间
		CacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), CacheConstans.USER_TIME);
		WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, (CacheConstans.USER_TIME/60/60/24));
		//缓存用户
		Map<String,Object> parameter = new HashMap<>();
		parameter.put("uuid",uuid);
		parameter.put("user",user);
		map.put("success",true);
		map.put("message","注册成功");
		map.put("entity",parameter);
		return map;
	}
	public User queryUserById(int userId) {
		return userDao.queryUserById(userId);
	}

	public boolean checkMobile(String mobile) {
		int count = userDao.checkMobile(mobile);
		return count > 0;
	}
	/**
	 * 用户登陆操作
	 */
	public Map<String,Object> queryDoUserLogin(Map<String,Object> parameterMap) throws Exception {
		Map map = new HashMap();
		String account = (String)parameterMap.get("account");
		String password = (String)parameterMap.get("password");
		String ipForget = (String)parameterMap.get("ipForget");
		HttpServletRequest request = (HttpServletRequest)parameterMap.get("request");
		HttpServletResponse response = (HttpServletResponse)parameterMap.get("response");
		User user = getLoginUser(account, MD5.getMD5(password));

		if(user==null){
			map.put("success",false);
			map.put("message","帐号或密码错误");
			return map;
		}
		String prefix = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
			if(StringUtils.isNotEmpty(prefix)){
			CacheUtil.remove(prefix);
		}
		if(user.getIsavalible()==2){
			map.put("success",false);
			map.put("message","帐号已被禁用");
			return map;
		}
		return queryDoLogin(user,ipForget,response,request);
	}
	/**
	 * 登陆操作
	 */
	public Map queryDoLogin(User user,String ipForget,HttpServletResponse response,HttpServletRequest request)throws Exception{
		Map map = new HashMap();
		//用户密码不能让别人看到
		user.setPassword("");

		String uuid = StringUtils.createUUID().replace("-", "");
		//当前时间戳
		Long currentTimestamp=System.currentTimeMillis();
		user.setLoginTimeStamp(currentTimestamp);

		if("true".equals(ipForget)){
			//缓存用户
			CacheUtil.set(uuid, user,CacheConstans.USER_TIME);
			//缓存用户cookie key(后台获取后，可以清除登录用户的缓存)
			CacheUtil.set(CacheConstans.WEB_USER_LOGIN_PREFIX+user.getUserId(), uuid,CacheConstans.USER_TIME);
			//缓存用户的登录时间
			CacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), CacheConstans.USER_TIME);
			WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, (CacheConstans.USER_TIME/60/60/24));
		}else{
			//缓存用户
			CacheUtil.set(uuid, user,86400);
			//缓存用户cookie key(后台获取后，可以清除登录用户的缓存)
			CacheUtil.set(CacheConstans.WEB_USER_LOGIN_PREFIX+user.getUserId(), uuid,CacheConstans.USER_TIME);
			//缓存用户的登录时间
			CacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), 86400);
			WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, 1);
		}

		//如果 是学员卡 注册的用户
		if(UserRegisterFrom.userCard.toString().equals(user.getRegisterFrom())&&ObjectUtils.isNotNull(cardCodeService)){
			//绑定学员卡中的课程
			cardCodeService.createLoginUserCouserOrder(user.getLoginAccount(), Long.valueOf(user.getUserId()));
			if(ObjectUtils.isNull(user.getCreateTime())){
				//修改注册时间
				user=this.queryUserById(user.getUserId());
				user.setCreateTime(new Date());
				this.updateUser(user);
				//用户密码不能让别人看到
				user.setPassword("");
			}
		}

		UserLoginLog loginLog =new UserLoginLog();
		loginLog.setIp(WebUtils.getIpAddr(request));
		loginLog.setLoginTime(new Date());
		String userAgent = WebUtils.getUserAgent(request);
		if(StringUtils.isNotEmpty(userAgent)){
			loginLog.setOsName(userAgent.split(";")[1]);
			loginLog.setUserAgent(userAgent.split(";")[0]);
		}
		loginLog.setUserId(user.getUserId());
		userLoginLogService.createLoginLog(loginLog);

		// 登录时把cookie中的购物车信息加到数据库中
		shopcartService.addTempShopCart(request, response, Long.valueOf(user.getUserId()));
		map.put("success",true);
		map.put("message","登陆成功");
		map.put("user",user);
		map.put("uuid",uuid);
		return map;
	}

	@Override
	public boolean checkUsername(String userName) {
		int count = userDao.checkUsername(userName);
		return count > 0;
	}

	public boolean checkEmail(String email) {
		int count = userDao.checkEmail(email);
		return count > 0;
	}
	public void updateImg(User user) {
		userDao.updateImg(user);
	}

	public User getLoginUser(String account,String password) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("password", password);
		return userDao.getLoginUser(map);
	}

	public void updateUserPwd(User user) {
		userDao.updateUserPwd(user);
		
	}

	public List<User> queryUserListPage(QueryUser query, PageEntity page) {
		return userDao.queryUserListPage(query, page);
	}

	public void updateUserStates(User user) {
		userDao.updateUserStates(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	/**
	 * 修改用户全部信息
	 */
	public void updateUserAll(User user){
		userDao.updateUserAll(user);
	}

	public void updateUserBannerUrl(User user) {
		userDao.updateUserBannerUrl(user);
	}

	public int queryAllUserCount() {
		return userDao.queryAllUserCount();
	}

	public User queryUserByEmailOrMobile(String emailOrMobile) {
		return userDao.queryUserByEmailOrMobile(emailOrMobile);
	}

	@Override
	public Map<String, User> queryCustomerInCusIds(List<Long> cusIds) throws Exception {
		if(ObjectUtils.isNotNull(cusIds)){
            Map<String, User> map = new HashMap<String, User>();
            // 通过传入的cusIds 查询customer
            List<User> queryCustomerList = userDao.queryUsersByIds(cusIds);
            // 整理数据放回map
            if (ObjectUtils.isNotNull(queryCustomerList)) {
                for (User queryCustomer : queryCustomerList) {
                    map.put(String.valueOf(queryCustomer.getUserId()), queryCustomer);
                }
            }
            return map;
        }else{
            return null;
        }
	}

	@Override
	public Map<String, User> getUserExpandByUids(String uids) throws Exception {
		String [] arrays=uids.split(",");
        List<Long> list = new ArrayList<Long>();
        for(String lo:arrays){
            if(StringUtils.isNotEmpty(lo)&&!"null".equals(lo)){
                list.add(Long.valueOf(lo));
            }
        }
        return queryCustomerInCusIds(list);
	}

	@Override
	public void updateUnReadMsgNumAddOne(String falg, Long cusId) {
		userDao.updateUnReadMsgNumAddOne(falg, cusId);
	}

	@Override
	public void updateUnReadMsgNumReset(String falg, Long cusId) {
		userDao.updateUnReadMsgNumReset(falg, cusId);
	}

	@Override
	public void updateCusForLST(Long cusId, Date date) {
		userDao.updateCusForLST(cusId, date);
	}

	@Override
	public String updateImportExcel(HttpServletRequest request,MultipartFile myFile,Integer mark) throws Exception {
		String msg="";
 		HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
				HSSFSheet sheet = wookbook.getSheet("Sheet1");

				int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
				if(rows==0){
					throw new BaseException("请填写数据");
				}
				for (int i = 1; i <= rows+1; i++) {
					// 读取左上端单元格
					HSSFRow row = sheet.getRow(i);
					// 行不为空
					if (row != null) {
						// **读取cell**
						String userName = getCellValue(row.getCell((short) 0));//账户
						String pwd = getCellValue(row.getCell((short) 1));//密码
						String email=getCellValue(row.getCell((short) 2));//邮箱
						String mobile = getCellValue(row.getCell((short) 3));//手机

				//账户
				if(StringUtils.isEmpty(userName)){
					if(mark==1){
						msg+="第" + (i+1) + "行账户不能为空<br/>";
						continue;
					}else{
						throw new BaseException("第" + (i+1) + "行账户不能为空<br/>");
					}
				}
				boolean b = checkUsername(userName);
				if (b==true) {
					if(mark==1){
						msg+="第"+(i+1)+"行账户已存在<br/>";
						continue;
					}else{
						throw new BaseException("第"+(i+1)+"行账户已存在<br/>");
					}
				}
				if (WebUtils.checkUsername(userName)==false) {
					if(mark==1){
						msg+="第"+(i+1)+"行账户格式错误<br/>";
						continue;
					}else{
						throw new BaseException("第"+(i+1)+"行账户格式错误<br/>");
					}
				}

				//密码
				if(StringUtils.isNotEmpty(pwd)){
					if(!WebUtils.isPasswordAvailable(pwd)){
						if(mark==1){
							msg+="第"+(i+1)+"行密码格式错误<br/>";
							continue;
						}else{
							throw new BaseException("第"+(i+1)+"行密码格式错误<br/>");
						}
					}
				}
				if(StringUtils.isEmpty(pwd)){
					pwd="123456";
				}

				//邮箱
				if(StringUtils.isNotEmpty(email)){
					if (WebUtils.checkEmail(email, 50)==false) {
						if(mark==1){
							msg+="第"+(i+1)+"行邮箱格式错误<br/>";
							continue;
						}else{
							throw new BaseException("第"+(i+1)+"行邮箱格式错误<br/>");
						}
					}
					b = checkEmail(email.toLowerCase());
					if (b==true) {
						if(mark==1){
							msg+="第"+(i+1)+"行邮箱已存在<br/>";
							continue;
						}else{
							throw new BaseException("第"+(i+1)+"行邮箱已存在<br/>");
						}
					}
				}

				//手机
				if(StringUtils.isNotEmpty(mobile)){
					if(!WebUtils.checkMobile(mobile)){
						if(mark==1){
							msg+="第"+(i+1)+"行手机格式错误<br/>";
							continue;
						}else{
							throw new BaseException("第"+(i+1)+"行手机格式错误<br/>");
						}
					}
					b = this.checkMobile(mobile);
					if (b==true) {
						if(mark==1){
							msg+="第"+(i+1)+"行手机已存在<br/>";
							continue;
						}else{
							throw new BaseException("第"+(i+1)+"行手机已存在<br/>");
						}
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
				userDao.createUser(user);//添加学员
			}
		}
		return msg;
	}

	/**
	 * 获得Hsscell内容
	 */
	public String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0");    
				value = df.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value.trim();
	}
	
	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 */
	public List<User> getUserListPage(User user, PageEntity page) {
		return userDao.getUserListPage(user, page);
	}

	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 * @return
	 */
	public List<User> getUserListAndCourse(User user, PageEntity page) {
		return userDao.getUserListAndCourse(user, page);
	}

	/**
	 * 根据id串查询用户信息
	 */
	public List<User> queryUserInIds(List<Long> cusIds) throws Exception{
		return userDao.queryUsersByIds(cusIds);
	}

	/**
	 * 更新缓存用户信息
	 */
	public Map<String,Object> setLoginInfo(HttpServletRequest request, int userId,String autoThirty){
		Map<String,Object> map = new HashMap<String,Object>();
		User user =this.queryUserById(userId);
		//用户密码不能让别人看到
		user.setPassword("");
		//用户cookie key
		String uuid = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
		//缓存用户的登录时间
		if(autoThirty!=null&&autoThirty.equals("true")){//自动登录
			CacheUtil.set(uuid, user, CacheConstans.USER_TIME);
		}else{
			CacheUtil.set(uuid, user, 86400);
		}
		map.put("uuid",uuid);
		map.put("user",user);
		return map;
	}

	@Override
	public boolean checkLoginAccount(String loginAccountPrefix) {
		int count = userDao.checkLoginAccount(loginAccountPrefix);
		return count <= 0;
	}
	/**
	 * 查询用户
	 * @param query 查询条件
	 * @return List<User>
	 */
	public List<User> queryUserList(QueryUser query){
		return userDao.queryUserList(query);
	}
}
