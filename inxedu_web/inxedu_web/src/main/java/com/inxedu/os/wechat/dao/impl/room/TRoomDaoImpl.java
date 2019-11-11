package com.inxedu.os.wechat.dao.impl.room;


import org.springframework.stereotype.Repository;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.wechat.dao.room.TRoomDao;
import com.inxedu.os.wechat.entity.room.TRoom;

/**
 * 学校dao层
 * @author lisheng
 */
@Repository("tRoomDao")
public class TRoomDaoImpl extends GenericDaoImpl implements TRoomDao {
	
	public void addRoom(TRoom room) {
		this.insert("TSchoolMapper.createSchool", room);
	}

}
