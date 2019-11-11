package com.inxedu.os.wechat.dao.impl.school;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.wechat.dao.school.TSchoolDao;
import com.inxedu.os.wechat.entity.school.TSchool;

/**
 * 学校dao层
 * @author lisheng
 */
@Repository("tSchoolDao")
public class TSchoolDaoImpl extends GenericDaoImpl implements TSchoolDao {
	
	public void addSchool(TSchool school) {
		this.insert("TSchoolMapper.createSchool", school);
	}

	public TSchool getSchoolByMap(Map<String,Object> map){
		return this.selectOne("TSchoolMapper.getSchoolByMap", map);
	}
	
	public TSchool querySchoolById(String id){
		return this.selectOne("TSchoolMapper.querySchoolById", id);
	}
	
}
