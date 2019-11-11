package com.inxedu.os.edu.service.invitationrecord;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecordDto;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.exception.AccountException;
import com.inxedu.os.edu.exception.StaleObjectStateException;

import java.util.List;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record InvitationRecordService接口
 */
public interface InvitationRecordService{
	/**
     * 添加edu_invitation_record
     */
    Long addInvitationRecord(InvitationRecord invitationRecord) throws AccountException, StaleObjectStateException;
    
    /**
     * 删除edu_invitation_record
     * @param id
     */
    void delInvitationRecordById(Long id);
    
    /**
     * 修改edu_invitation_record
     * @param invitationRecord
     */
    void updateInvitationRecord(InvitationRecord invitationRecord);
    
    /**
     * 通过id，查询edu_invitation_record
     * @param id
     * @return
     */
    InvitationRecord getInvitationRecordById(Long id);
    
    /**
     * 分页查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @param page 分页条件
     * @return List<InvitationRecordDto>
     */
    List<InvitationRecordDto> queryInvitationRecordListPage(InvitationRecord invitationRecord, PageEntity page);
    
    /**
     * 条件查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @return List<InvitationRecord>
     */
    List<InvitationRecord> queryInvitationRecordList(InvitationRecord invitationRecord);

    /**
     * 分销消费 返现给邀请人
     * @param order 订单参数
     */
    void consumeCashback(Order order) throws AccountException, StaleObjectStateException;
}



