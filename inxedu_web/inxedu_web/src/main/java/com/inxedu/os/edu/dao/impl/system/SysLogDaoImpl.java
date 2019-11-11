package com.inxedu.os.edu.dao.impl.system;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.system.SysLogDao;
import com.inxedu.os.edu.entity.system.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Repository("sysLogDao")
public class SysLogDaoImpl extends GenericDaoImpl implements SysLogDao {
	/**
	 * 添加操作日志
	 */
	public void createSysLog(SysLog sysLog){
		this.insert("SysLogMapper.createSysLog", sysLog);
	}
	/**
	 * 分页查询操作日志
	 */
	public List<SysLog> querySysLogPage(SysLog sysLog, PageEntity page){
		return this.queryForListPageCount("SysLogMapper.querySysLogPage",sysLog,page);
	}
	/**
	 * 查询操作日志
	 */
	public SysLog querySysLogById(SysLog sysLog){
		return this.selectOne("SysLogMapper.querySysLogById",sysLog);
	}
	/**
	 * 删除操作日志
	 */
	public void deleteSysLog(SysLog sysLog){
		this.delete("SysLogMapper.deleteSysLog",sysLog);
	}
}
