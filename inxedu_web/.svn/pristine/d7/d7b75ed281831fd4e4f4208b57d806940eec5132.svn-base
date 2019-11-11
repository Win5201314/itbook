package com.inxedu.os.wechat.dao.impl.user;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.wechat.dao.user.TUserDao;
import com.inxedu.os.wechat.entity.user.TUser;

/**
 * 学校dao层
 * @author lisheng
 */
@Repository("tUserDao")
public class TUserDaoImpl extends GenericDaoImpl implements TUserDao {
	
	public void addUser(TUser user) {
		this.insert("TUserMapper.createUser", user);
	}

	public TUser getUserByMap(Map<String,Object> map){
		return this.selectOne("TUserMapper.getUserByMap", map);
	}
	
	public void updateUserByMap(Map<String,Object> map){
		 this.update("TUserMapper.updateUserByMap", map);
	}
	
}
