package com.inxedu.os.common.service;


import com.google.gson.GsonBuilder;
import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.WebLoginUser;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 *webservice 接口
 */
@Service("webHessianService")
public class WebHessianServiceImpl implements WebHessianService {
	private static final Logger logger = LoggerFactory.getLogger(WebHessianServiceImpl.class);


	@Autowired
	private UserService userService;
	/**
	 * 查询用户信息
	 * @param cusId
	 * @return
	 */
	public Map<String,String> getUserInfo(Long cusId){
		Map<String,String> map=new HashMap<String, String>();
		try {
			//用户信息
	        User user= userService.queryUserById(Integer.parseInt(cusId+""));
	        WebLoginUser webLoginUser=null;
	        if (user!=null) {
	        	webLoginUser=new WebLoginUser();
	        	//添加需要的属性
				webLoginUser.setCusId(Long.valueOf(user.getUserId()));
				webLoginUser.setEmail(user.getEmail());
				webLoginUser.setGender(user.getSex());
				webLoginUser.setId(Long.valueOf(user.getUserId()));
				webLoginUser.setMobile(user.getMobile());
				webLoginUser.setNickname(user.getShowName());
				webLoginUser.setRealname(user.getUserName());
				webLoginUser.setAvatar(user.getPicImg());
				webLoginUser.setUserInfo("这个人很懒，他还没有签名");
			}
	        //转Json
	        String userJson= FastJsonUtil.obj2JsonString(webLoginUser).toString();
	        map.put("userJson", userJson);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.getUserInfo-----error",e);
		}
		return map;
	}

	/**
	 * 批量查询用户信息
	 * @param cusIds
	 * @return
	 */
	public Map<String,String> getUserInfoByUids(String cusIds){
		Map<String,String> userMap=new HashMap<String, String>();
		try {
			//批量用户信息
			Map<String, User> map=userService.getUserExpandByUids(cusIds);
			Map<String, WebLoginUser> webUserMap = new HashMap<String, WebLoginUser>();
			WebLoginUser webLoginUser=null;
			User user=null;
			//添加需要的属性
			for(Entry<String, User> entry: map.entrySet()) { 
				webLoginUser=new WebLoginUser();
				user=entry.getValue();
				webLoginUser.setCusId(Long.valueOf(user.getUserId()));
				webLoginUser.setEmail(user.getEmail());
				webLoginUser.setGender(user.getSex());
				webLoginUser.setId(Long.valueOf(user.getUserId()));
				webLoginUser.setMobile(user.getMobile());
				webLoginUser.setNickname(user.getShowName());
				webLoginUser.setRealname(user.getUserName());
				webLoginUser.setAvatar(user.getPicImg());
				webLoginUser.setUserInfo("这个人很懒，他还没有签名");
				webUserMap.put(String.valueOf(user.getUserId()), webLoginUser);
			} 
			
			//转为String用户集合
			List<String> userExpandDtos=new ArrayList<String>();
			for(Entry<String, WebLoginUser> entry: webUserMap.entrySet()) { 
				userExpandDtos.add(FastJsonUtil.obj2JsonString(entry.getValue()));
			} 
			//转Json
			String usersJson=FastJsonUtil.obj2JsonString(userExpandDtos).toString();
			userMap.put("usersJson", usersJson);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.getUserInfoByUids-----error",e);
		}
		return userMap;
	}

	/**
	 * 小组  分页查询用户
	 * @return
	 */
	public Map<String,String> queryUserExpandDtoPage(Map<String, String> map){
		String queryKeyWord = map.get("queryKeyWord");//
		int pageSize = Integer.parseInt(map.get("pageSize"));//分页size
		int currentPage = Integer.parseInt(map.get("currentPage"));//当前页数
		PageEntity page = new PageEntity();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		//用户集合
		List<String> usersString = new ArrayList<String>();
		QueryUser query = new  QueryUser();
		query.setKeyWord(queryKeyWord);
		List<User> userList = userService.queryUserListPage(query,page);
		if (ObjectUtils.isNotNull(userList)) {
			for (User u : userList) {
				WebLoginUser webLoginUser=new WebLoginUser();
				webLoginUser.setCusId(Long.valueOf(u.getUserId()));
				webLoginUser.setEmail(u.getEmail());
				webLoginUser.setGender(u.getSex());
				webLoginUser.setId(Long.valueOf(u.getUserId()));
				webLoginUser.setMobile(u.getMobile());
				webLoginUser.setNickname(u.getShowName());
				webLoginUser.setRealname(u.getUserName());
				webLoginUser.setAvatar(u.getPicImg());
				webLoginUser.setUserInfo("这个人很懒，他还没有签名");
				usersString.add(FastJsonUtil.obj2JsonString(webLoginUser));
			}
		}
		//转成json
		String usersJson = FastJsonUtil.obj2JsonString(usersString);
		String pageJson = FastJsonUtil.obj2JsonString(page);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("usersJson", usersJson);
		resultMap.put("pageJson", pageJson);
		return resultMap;
	}

	/**
	 * 传入key获得缓存对象
	 */
	public Object getEHCache(String key){


		return CacheUtil.get(key);
	}
}
