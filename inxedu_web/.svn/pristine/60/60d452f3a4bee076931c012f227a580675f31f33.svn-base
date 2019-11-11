package com.inxedu.os.edu.service.impl.user;

import com.inxedu.os.edu.dao.user.UserLoginLogDao;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.inxedu.os.common.entity.PageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService{

	@Autowired
	private UserLoginLogDao userLoginLogDao;
	
	public int createLoginLog(UserLoginLog loginLog) {
		return userLoginLogDao.createLoginLog(loginLog);
	}
	
	public List<UserLoginLog> queryUserLogPage(int userId, PageEntity page) {
		return userLoginLogDao.queryUserLogPage(userId, page);
	}
	/**
	 * 查询用户登录日志
	 */
	public List<UserLoginLog> queryUserLoginLog(UserLoginLog userLoginLog){
		return userLoginLogDao.queryUserLoginLog(userLoginLog);
	}

	@Override
	public void delUserLoginLogByCondition(UserLoginLog userLoginLog) {
		userLoginLogDao.delUserLoginLogByCondition(userLoginLog);
	}
}
