package com.inxedu.os.wechat.service.impl.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.wechat.dao.user.TUserDao;
import com.inxedu.os.wechat.entity.user.TUser;
import com.inxedu.os.wechat.service.user.TUserService;

/**
 * User管理接口
 * @author lisheng
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
	@Autowired
	private TUserDao userDao;

	public void addUser(TUser user) {
		userDao.addUser(user);
	}
	
	public TUser getUserByMap(Map<String,Object> map) {
		return userDao.getUserByMap(map);
	}

	public void updateUserByMap(Map<String,Object> map){
		 userDao.updateUserByMap(map);
	}
}