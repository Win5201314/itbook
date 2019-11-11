package com.inxedu.os.edu.service.impl.letter;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.dao.letter.MsgSystemDao;
import com.inxedu.os.edu.dao.order.TrxorderDetailDao;
import com.inxedu.os.edu.entity.letter.MsgSystem;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.letter.MsgSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : xiaokun
 * @ClassName com.inxedu.os.sns.service.impl.letter.MsgSystemServiceImpl
 * @description 系统消息
 * @Create Date : 2014-1-26 下午1:55:11
 * @author www.inxedu.com
 */
@Service("msgSystemService")
public class MsgSystemServiceImpl implements MsgSystemService {

    @Autowired
    private MsgSystemDao msgSystemDao;
    @Autowired
    private TrxorderDetailDao trxorderDetailDao;
    @Autowired
    private MsgReceiveService msgReceiveService;
    Logger logger = LoggerFactory.getLogger(MsgReceiveServiceImpl.class);


    public Long addMsgSystem(MsgSystem msgSystem) throws Exception {
        return msgSystemDao.addMsgSystem(msgSystem);
    }

    /**
     * 批量添加系统消息
     */
    public void addMsgSystemBatch(List<MsgSystem> msgSystemList) {
        msgSystemDao.addMsgSystemBatch(msgSystemList);
    }

    /**
     * 查询系统消息
     */
    public List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception {
        return msgSystemDao.queryMsgSystemList(msgSystem, page);
    }

    /**
     * 通过id删除系统消息
     */
    public Long delMsgSystemById(String id) throws Exception {
        return msgSystemDao.delMsgSystemById(id);
    }

    /**
     * 查询大于传入的时间的系统系统消息
     */
    public List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception {
        return msgSystemDao.queryMSListByLT(lastTime);
    }

    /**
     * 检查系统消息过期更新字段 删除过期的站内信
     */
    public void updatePast() throws Exception {
        System.out.println("**********************updatePast***************");
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 180);
        Date endDate = dft.parse(dft.format(date.getTime()));
        msgReceiveService.delMsgReceivePast(endDate);
        msgSystemDao.updateMsgSystemPastTime(endDate);
       
    }
    @Override
    public void timeOverMsg() {
        try {
            /*查询即将过去的课程信息*/
            List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailDao.getTimeOverOrder();
            for (QueryTrxorderDetail queryTrxorderDetail:queryTrxorderDetailList){
                String emailTitle = "课程过期提示！";
                msgReceiveService.sendMessage(Integer.parseInt(queryTrxorderDetail.getUserId().toString()), emailTitle, MsgType.timeOverMsg.toString(), true,queryTrxorderDetail.getCourseName() );
            }

        }catch (Exception e){
            logger.error("MsgSystemServiceImpl---timeOverMsg is error", e);
        }
    }
}
