package com.inxedu.os.edu.dao.impl.user;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.user.UserLoginLogDao;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Repository("userLoginLogDao")
public class UserLoginLogDaoImpl extends GenericDaoImpl implements UserLoginLogDao {

	
	public int createLoginLog(UserLoginLog loginLog) {
		this.insert("UserLoginLogMapper.createLoginLog", loginLog);
		return loginLog.getLogId();
	}

	
	public List<UserLoginLog> queryUserLogPage(int userId, PageEntity page) {
		return this.queryForListPage("UserLoginLogMapper.queryUserLogPage", userId, page);
	}
	/**
	 * 查询用户登录日志
	 */
	public List<UserLoginLog> queryUserLoginLog(UserLoginLog userLoginLog){
		return this.selectList("UserLoginLogMapper.queryUserLoginLog", userLoginLog);
	}

	@Override
	public void delUserLoginLogByCondition(UserLoginLog userLoginLog) {
		this.delete("UserLoginLogMapper.delUserLoginLogByCondition", userLoginLog);
	}
}
