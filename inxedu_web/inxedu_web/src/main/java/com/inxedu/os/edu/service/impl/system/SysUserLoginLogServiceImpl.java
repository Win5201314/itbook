package com.inxedu.os.edu.service.impl.system;

import com.inxedu.os.edu.dao.system.SysUserLoginLogDao;
import com.inxedu.os.edu.entity.system.SysUserLoginLog;
import com.inxedu.os.edu.service.system.SysUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.inxedu.os.common.entity.PageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Service("sysUserLoginLogService")
public class SysUserLoginLogServiceImpl implements SysUserLoginLogService {

	@Autowired
	private SysUserLoginLogDao sysUserLoginLogDao;
	
	public int createLoginLog(SysUserLoginLog loginLog) {
		return sysUserLoginLogDao.createLoginLog(loginLog);
	}
	
	public List<SysUserLoginLog> queryUserLogPage(int userId, PageEntity page) {
		return sysUserLoginLogDao.queryUserLogPage(userId, page);
	}
	/**
	 * 查询用户登录日志
	 */
	public List<SysUserLoginLog> queryUserLog(SysUserLoginLog sysUserLoginLog){
		return sysUserLoginLogDao.queryUserLog(sysUserLoginLog);
	}

	@Override
	public void delSysLogByCondition(SysUserLoginLog sysUserLoginLog) {
		sysUserLoginLogDao.delSysLogByCondition(sysUserLoginLog);
	}
}
