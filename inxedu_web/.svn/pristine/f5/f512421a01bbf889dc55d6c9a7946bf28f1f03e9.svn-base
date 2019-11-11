package com.inxedu.os.edu.service.system;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.system.SysLog;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface SysLogService {
	/**
	 * 添加操作日志
	 */
	void createSysLog(SysLog sysLog);
	/**
	 * 添加操作日志
	 * 类型：add添加 update 更新 del 删除操作
	 */
	void createSysLog(int adminUserId, String type, Object content, String operation);
	/**
	 * 分页查询操作日志
	 */
	List<SysLog> querySysLogPage(SysLog sysLog, PageEntity page);
	/**
	 * 查询操作日志
	 */
	SysLog querySysLogById(SysLog sysLog);
	/**
	 * 删除操作日志
	 */
	void deleteSysLog(SysLog sysLog);
}
