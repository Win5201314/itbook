package com.inxedu.os.edu.service.impl.invitationrecord;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.constants.enums.account.AccountType;
import com.inxedu.os.edu.constants.enums.order.OrderType;
import com.inxedu.os.edu.dao.invitationrecord.InvitationRecordDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecord;
import com.inxedu.os.edu.entity.invitationrecord.InvitationRecordDto;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.exception.AccountException;
import com.inxedu.os.edu.exception.StaleObjectStateException;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record InvitationRecordService接口实现
 */
@Service("invitationRecordService")
public class InvitationRecordServiceImpl implements InvitationRecordService {
	@Autowired
	private InvitationRecordDao invitationRecordDao;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;

	/**
     * 添加edu_invitation_record
     */
    public Long addInvitationRecord(InvitationRecord invitationRecord) throws AccountException, StaleObjectStateException {
        //服务开关
        Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
        //分销配置
        Map<String, Object> invite = (Map<String, Object>) websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.invite.toString()).get(WebSiteProfileType.invite.toString());
        invitationRecord.setAddTime(new Date());
        invitationRecord.setCashback(new BigDecimal(invite.get("cashback") + ""));
        Long recordId = invitationRecordDao.addInvitationRecord(invitationRecord);
        if ("ON".equals(serviceSwitch.get("invite")+"") && recordId.intValue() > 0) {
            UserAccount userAccount = userAccountService.getUserAccountByUserId(Long.valueOf(invitationRecord.getUserId()));
            //给邀请人返现充值
            String out_no = "timeOrder"+System.currentTimeMillis();//系统充值没有订单，用时间戳代替不同的订单号
            userAccountService.credit(userAccount, new BigDecimal(invite.get("cashback")+""), AccountType.BACK, AccountHistoryType.BACKMONEY, Long.valueOf(invitationRecord.getUserId()), 0L, out_no, out_no, new Date(), "邀请返现", true, AccountBizType.ADMIN);
        }
        return recordId;
    }

    
    /**
     * 删除edu_invitation_record
     * @param id
     */
    public void delInvitationRecordById(Long id){
    	invitationRecordDao.delInvitationRecordById(id);
    }
    
    /**
     * 修改edu_invitation_record
     * @param invitationRecord
     */
    public void updateInvitationRecord(InvitationRecord invitationRecord){
    	invitationRecordDao.updateInvitationRecord(invitationRecord);
    }
    
    /**
     * 通过id，查询edu_invitation_record
     * @param id
     * @return
     */
    public InvitationRecord getInvitationRecordById(Long id){
    	return invitationRecordDao.getInvitationRecordById(id);
    }
    
    /**
     * 分页查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @param page 分页条件
     * @return List<InvitationRecord>
     */
    public List<InvitationRecordDto> queryInvitationRecordListPage(InvitationRecord invitationRecord, PageEntity page){
    	return invitationRecordDao.queryInvitationRecordListPage(invitationRecord, page);
    }
    
    /**
     * 条件查询edu_invitation_record列表
     * @param invitationRecord 查询条件
     * @return List<InvitationRecord>
     */
    public List<InvitationRecord> queryInvitationRecordList(InvitationRecord invitationRecord){
    	return invitationRecordDao.queryInvitationRecordList(invitationRecord);
    }

    @Override
    public void consumeCashback(Order order) throws AccountException, StaleObjectStateException {
        //购买服务开关
        Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
        //分销配置 , 根据返现配置返现
        Map<String, Object> invite = (Map<String, Object>) websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.invite.toString()).get(WebSiteProfileType.invite.toString());
        if ("ON".equals(serviceSwitch.get("invite")) && "ON".equals(invite.get("withdrawCash"))) {

            //查询 用户是否 是 被邀请的
            InvitationRecord invitationRecord=new InvitationRecord();
            invitationRecord.setInvitationUserId(Long.valueOf(order.getUserId()));
            List<InvitationRecord> invitationRecordList=this.queryInvitationRecordList(invitationRecord);
            if(ObjectUtils.isNotNull(invitationRecordList)){
                // userAccount要重新查询一次，否则乐观锁版本号异常
                UserAccount userAccount = userAccountService.getUserAccountByUserId(invitationRecordList.get(0).getUserId());
                BigDecimal backMoney = new BigDecimal(0);
                if((OrderType.COURSE+"").equals(order.getOrderType())){
                    //订单流水表
                    QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
                    queryTrxorderDetail.setTrxorderId(new Long(order.getOrderId()));
                    List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
                    for(QueryTrxorderDetail queryTrxorderDetail1:queryTrxorderDetailList){
                        BigDecimal sellTypePencent=new BigDecimal(invite.get("coursePercent")+"");
                        if("LIVE".equals(queryTrxorderDetail1.getSellType())){
                            sellTypePencent=new BigDecimal(invite.get("livePercent")+"");
                        }else if("PACKAGE".equals(queryTrxorderDetail1.getSellType())){
                            sellTypePencent=new BigDecimal(invite.get("packagePercent")+"");
                        }
                        //backMoney += (课程价格/订单总金额)*实际支付cash金额 * 返现比例
                        backMoney=backMoney.add(  queryTrxorderDetail1.getCurrentPrice().divide(order.getOrderAmount()).multiply(order.getCashAmount()).multiply( sellTypePencent.divide(new BigDecimal(100)) )  );
                    }
                }else if((OrderType.MEMBER+"").equals(order.getOrderType())){
                    backMoney=order.getCashAmount().multiply(new BigDecimal(invite.get("memberPercent")+"").divide(new BigDecimal(100)));
                }
                //充值不返现
                /*else if((OrderType.ACCOUNT+"").equals(order.getOrderType())){
                    backMoney=order.getCashAmount().multiply(new BigDecimal(invite.get("accountPercent")+"").divide(new BigDecimal(100)));
                }*/

                //提现金额大于 等于 0.01 才返
                if(backMoney.compareTo(new BigDecimal("0.01"))>=0){
                    //给邀请人返现充值
                    String out_no = "timeOrder"+System.currentTimeMillis();//系统充值没有订单，用时间戳代替不同的订单号
                    userAccountService.credit(userAccount, backMoney, AccountType.BACK, AccountHistoryType.BACKMONEY, userAccount.getUserId(), 0L, out_no, out_no, new Date(), "邀请消费返现", true, AccountBizType.ADMIN);
                }
            }

        }

    }
}



