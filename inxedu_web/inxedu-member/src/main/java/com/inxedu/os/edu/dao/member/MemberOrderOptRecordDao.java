package com.inxedu.os.edu.dao.member;

import com.inxedu.os.edu.entity.member.MemberOrderOptRecord;

/**
 * 
 * @ClassName com.yizhilu.os.edu.dao.member.MemberOrderOptRecordDao
 * @description
 * @author :xujunbao
 * @Create Date : 2014年10月29日 下午4:44:58
 */
public interface MemberOrderOptRecordDao {
	/**
	 * 添加会员操作记录
	 * 
	 * @param memberOrderOptRecord
	 * @return
	 */
    Long addMemberOrderOptRecord(MemberOrderOptRecord memberOrderOptRecord);
}
