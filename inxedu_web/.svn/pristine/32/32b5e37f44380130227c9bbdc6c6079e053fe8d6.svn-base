package com.inxedu.os.edu.dao.impl.invitationrecord;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.invitationrecord.InvitationRecordDao;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record InvitationRecordDao接口实现
 */
@Repository("invitationRecordDao")
public class InvitationRecordDaoImpl extends GenericDaoImpl implements InvitationRecordDao{
	/**
     * 添加edu_invitation_record
     */
    public Long addInvitationRecord(InvitationRecord invitationRecord){
    	this.insert("InvitationRecordMapper.addInvitationRecord", invitationRecord);
		return invitationRecord.getId();
    }
    
    /**
     * 删除edu_invitation_record
     * @param id
     */
    public void delInvitationRecordById(Long id){
    	this.update("InvitationRecordMapper.delInvitationRecordById", id);
    }
    
    /**
     * 修改edu_invitation_record
     * @param invitationRecord
     */
    public void updateInvitationRecord(InvitationRecord invitationRecord){
    	this.update("InvitationRecordMapper.updateInvitationRecord", invitationRecord);
    }
    
    /**
     * 通过id，查询edu_invitation_record
     * @param id
     * @return
     */
    public InvitationRecord getInvitationRecordById(Long id){
    	return this.selectOne("InvitationRecordMapper.getInvitationRecordById", id);
    }
    
    /**
     * 分页查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @param page 分页条件
     * @return List<InvitationRecordDto>
     */
    public List<InvitationRecordDto> queryInvitationRecordListPage(InvitationRecord invitationRecord, PageEntity page){
    	return this.queryForListPage("InvitationRecordMapper.queryInvitationRecordListPage", invitationRecord, page);
    }
    
    /**
     * 条件查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @return List<InvitationRecord>
     */
    public List<InvitationRecord> queryInvitationRecordList(InvitationRecord invitationRecord){
    	return this.selectList("InvitationRecordMapper.queryInvitationRecordList", invitationRecord);
    }
}



