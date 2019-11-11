package com.inxedu.os.edu.service.impl.system;

import com.google.gson.GsonBuilder;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.edu.dao.system.SysLogDao;
import com.inxedu.os.edu.entity.system.SysLog;
import com.inxedu.os.edu.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;
	
	public void createSysLog(SysLog sysLog) {
		sysLog.setCreateTime(new Date());
		sysLogDao.createSysLog(sysLog);
	}

	/**
	 * 添加操作日志
	 * 类型：add添加 update 更新 del 删除操作
	 */
	public void createSysLog(int adminUserId,String type,Object content,String operation){
		SysLog sysLog = new SysLog();
		sysLog.setAdminUserId(adminUserId);//相关操作id
		sysLog.setType(type);//操作类型
		sysLog.setContent(FastJsonUtil.obj2JsonString(content));//相关对象的json数据
		sysLog.setOperation(operation);//操作描述
		sysLog.setCreateTime(new Date());//添加时间
		sysLogDao.createSysLog(sysLog);
	}
	public List<SysLog> querySysLogPage(SysLog sysLog, PageEntity page) {
		return sysLogDao.querySysLogPage(sysLog,page);
	}
	/**
	 * 查询操作日志
	 */
	public SysLog querySysLogById(SysLog sysLog){
		return sysLogDao.querySysLogById(sysLog);
	}
	/**
	 * 删除操作日志
	 */
	public void deleteSysLog(SysLog sysLog){
		sysLogDao.deleteSysLog(sysLog);
	}
}
