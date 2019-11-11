package com.inxedu.os.edu.dao.dataclean;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.dataclean.DataClean;

import java.util.List;

/**
 * 数据清理dao层接口
 * @author www.inxedu.com
 */
public interface DataCleanDao {
	/**
	 * 数据清理分页
	 */
	public List<DataClean> getDataCleanByPage(DataClean dataClean, PageEntity page);
	/**
	 * 查询数据清理
	 */
	public DataClean queryDataCleanById(int id);
	/**
	 * 执行传入id的sql
	 */
	public void queryDoSql(int id);
	/**
	 * 添加数据清理
	 */
	public void addDataClean(DataClean dataClean);
	/**
	 * 更新数据清理记录
	 */
	public void updateDataClean(DataClean dataCleans);
	/**
	 * 删除数据清理记录
	 */
	public void delDataClean(int id);
}
