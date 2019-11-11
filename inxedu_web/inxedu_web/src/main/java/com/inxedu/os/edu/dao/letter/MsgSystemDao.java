package com.inxedu.os.edu.dao.letter;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.letter.MsgSystem;

import java.util.Date;
import java.util.List;


/**
 * @author : xiaokun
 * @ClassName com.inxedu.os.sns.dao.letter.MsgSenderDao
 * @description 站内信发件箱的Dao
 * @Create Date : 2014-1-26 下午1:58:49
 * @author www.inxedu.com
 */
public interface MsgSystemDao {
    /**
     * 添加系统消息
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
     * 更新过期的系统消息的字段为过期
     */
    void updateMsgSystemPastTime(Date lastTime) throws Exception;
}
