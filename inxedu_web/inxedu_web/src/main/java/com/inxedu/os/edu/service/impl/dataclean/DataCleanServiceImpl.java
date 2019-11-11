package com.inxedu.os.edu.service.impl.dataclean;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.dataclean.DataCleanDao;
import com.inxedu.os.edu.entity.dataclean.DataClean;
import com.inxedu.os.edu.service.dataclean.DataCleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.inxedu.com
 */
@Service("dataCleanService")
public class DataCleanServiceImpl implements DataCleanService {

	@Autowired
	private DataCleanDao dataCleanDao;
	@Override
	public List<DataClean> getDataCleanByPage(DataClean dataClean, PageEntity page) {
		return dataCleanDao.getDataCleanByPage(dataClean,page);
	}

	@Override
	public void addDataClean(DataClean dataClean) {
		dataCleanDao.addDataClean(dataClean);
	}
	/**
	 * 执行传入id的sql
	 */
	public void queryDoSql(int id){
		dataCleanDao.queryDoSql(id);
	}
	/**
	 * 查询数据清理
	 */
	public DataClean queryDataCleanById(int id){
		return dataCleanDao.queryDataCleanById(id);
	}
	@Override
	public void updateDataClean(DataClean dataCleans) {
		dataCleanDao.updateDataClean(dataCleans);
	}

	@Override
	public void delDataClean(int id) {
		dataCleanDao.delDataClean(id);
	}
}
