package com.inxedu.os.wechat.service.impl.school;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.wechat.dao.school.TSchoolDao;
import com.inxedu.os.wechat.entity.school.TSchool;
import com.inxedu.os.wechat.service.school.TSchoolService;

/**
 * School管理接口
 * @author lisheng
 */
@Service("tSchoolService")
public class TSchoolServiceImpl implements TSchoolService {
	@Autowired
	private TSchoolDao schoolDao;

	public void addSchool(TSchool school) {
		schoolDao.addSchool(school);
	}
	
	public TSchool getSchoolByMap(Map<String,Object> map) {
		return schoolDao.getSchoolByMap(map);
	}

	public TSchool querySchoolById(String id){
		return schoolDao.querySchoolById(id);
	}
}