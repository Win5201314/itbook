package com.inxedu.os.edu.dao.impl.member;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.member.MemberOrderOptRecordDao;
import com.inxedu.os.edu.entity.member.MemberOrderOptRecord;
import org.springframework.stereotype.Repository;



/**
 * 
 * @ClassName  com.yizhilu.os.edu.dao.impl.member.MemberOrderOptRecordDaoImpl
 * @description
 * @author :xujunbao
 * @Create Date : 2014年10月29日 下午4:46:36
 */
@Repository("memberOrderOptRecordDao")
public class MemberOrderOptRecordDaoImpl extends GenericDaoImpl implements MemberOrderOptRecordDao {

	/**
	 * 添加会员操作记录
	 * 
	 * @param memberOrderOptRecord
	 * @return
	 */
	public Long addMemberOrderOptRecord(MemberOrderOptRecord memberOrderOptRecord) {
		return this.insert("MemberOrderOptRecordMapper.addMemberOrderOptRecord", memberOrderOptRecord);
	}

}
