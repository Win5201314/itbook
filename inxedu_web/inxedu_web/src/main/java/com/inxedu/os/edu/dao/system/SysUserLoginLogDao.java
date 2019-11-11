package com.inxedu.os.edu.dao.system;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.system.SysUserLoginLog;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface SysUserLoginLogDao {
	/**
	 * 添加登录日志
	 * @param loginLog
	 * @return 日志ID
	 */
	int createLoginLog(SysUserLoginLog loginLog);
	
	/**
	 * 查询用户登录日志
	 * @param userId 用户ID
	 * @param page 分页条件
	 * @return List<SysUserLoginLog>
	 */
	List<SysUserLoginLog> queryUserLogPage(int userId, PageEntity page);
	/**
	 * 查询用户登录日志
	 */
	List<SysUserLoginLog> queryUserLog(SysUserLoginLog sysUserLoginLog);

	/**
	 * 根据条件删除 系统用户登录日志
	 */
	void delSysLogByCondition(SysUserLoginLog sysUserLoginLog);
}
