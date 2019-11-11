package com.inxedu.os.edu.service.user;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.user.UserLoginLog;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface UserLoginLogService {
	/**
	 * 添加登录日志
	 * @param loginLog
	 * @return 日志ID
	 */
	int createLoginLog(UserLoginLog loginLog);
	
	/**
	 * 查询用户登录日志
	 * @param userId 用户ID
	 * @param page 分页条件
	 * @return List<UserLoginLog>
	 */
	List<UserLoginLog> queryUserLogPage(int userId, PageEntity page);
	/**
	 * 查询用户登录日志
	 */
	List<UserLoginLog> queryUserLoginLog(UserLoginLog userLoginLog);

	/**
	 * 根据条件删除 用户登录日志
	 */
	void delUserLoginLogByCondition(UserLoginLog userLoginLog);
}
