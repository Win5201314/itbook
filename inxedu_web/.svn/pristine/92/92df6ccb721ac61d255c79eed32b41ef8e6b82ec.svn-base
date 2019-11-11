package com.inxedu.os.edu.dao.impl.dataclean;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.dataclean.DataCleanDao;
import com.inxedu.os.edu.entity.dataclean.DataClean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论dao层实现
 * @author www.inxedu.com
 */
@Repository("dataCleanDao")
public class DataCleanDaoImpl extends GenericDaoImpl implements DataCleanDao {

	public List<DataClean> getDataCleanByPage(DataClean dataClean, PageEntity page) {
		return this.queryForListPageCount("DataCleanMapper.queryDataCleanPage", dataClean, page);
	}
	/**
	 * 查询数据清理
	 */
	public DataClean queryDataCleanById(int id){
		return this.selectOne("DataCleanMapper.queryDataCleanById", id);
	}
	/**
	 * 执行传入id的sql
	 */
	public void queryDoSql(int id){
		DataClean dataClean = this.selectOne("DataCleanMapper.queryDataCleanById", id);
		this.selectOne("DataCleanMapper.queryDoSql", dataClean.getSql());
	}
	public void addDataClean(DataClean dataClean) {
		this.insert("DataCleanMapper.addDataClean", dataClean);
	}
	/**
	 * 更新评论
	 */
	public void updateDataClean(DataClean dataClean){
		this.update("DataCleanMapper.updateDataClean", dataClean);
	}

	/**
	 * 删除评论
	 */
	public void delDataClean(int id){
		this.delete("DataCleanMapper.delDataClean", id);
	}

}
