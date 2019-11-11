package com.inxedu.os.edu.service.letter;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.letter.MsgSystem;

import java.util.Date;
import java.util.List;

/**
 * @description 站内信的发件箱service
 * @author www.inxedu.com
 */
public interface MsgSystemService {

    /**
     * 添加系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    Long addMsgSystem(MsgSystem msgSystem) throws Exception;

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    void addMsgSystemBatch(List<MsgSystem> msgSystemList);

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception;

    /**
     * 通过id删除系统消息
     * @return
     * @throws Exception
     */
    Long delMsgSystemById(String id) throws Exception;


    /**
     * 查询大于传入的时间的系统系统消息
     * @return
     * @throws Exception
     */
    List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception;

    /**
     * 检查系统消息过期更新字段 删除过期的站内信
     * @return
     * @throws Exception
     */
    void updatePast() throws Exception;
    /*
    *给明天有课程或优惠券过期的用户发送给消息
    */
    void timeOverMsg();

}
