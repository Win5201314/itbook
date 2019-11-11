package com.inxedu.os.edu.service.impl.letter;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.dao.letter.MsgReceiveDao;
import com.inxedu.os.edu.entity.letter.LetterConstans;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.MsgSystem;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.letter.MsgSystemService;
import com.inxedu.os.edu.service.mobile.SmsUtil;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaokun
 * @ClassName msgReceiveServiceImpl
 * @package com.inxedu.open.sns.service.impl.letter
 * @description 站内信的实现
 * @Create Date: 2013-12-10 下午4:14:32
 * @author www.inxedu.com
 */
@Service("msgReceiveService")
public class MsgReceiveServiceImpl implements MsgReceiveService {
	//logger
	Logger logger = LoggerFactory.getLogger(MsgReceiveServiceImpl.class);
    @Autowired
    private MsgReceiveDao msgReceiveDao;
    @Autowired
    private UserService userService;
    @Autowired
    private MsgSystemService msgSystemService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private EmailService emailService;

    /**
     * 查询站内信收件箱
     */
    public List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception {
    	//查询用户信息
   	    User user  = userService.queryUserById((Integer.parseInt(msgReceive.getReceivingCusId().toString())));
        Date lastTime = user.getLastSystemTime();
        //查询系统发送的未读消息
        List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);
        if (ObjectUtils.isNotNull(MSlist)) {
            List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
            //查出未读的系统消息插入到系统中 更新
            for (MsgSystem mgstm : MSlist) {
                MsgReceive msgReceive1 = new MsgReceive();
                msgReceive1.setContent(mgstm.getContent());
                msgReceive1.setAddTime(new Date());
                msgReceive1.setReceivingCusId(msgReceive.getReceivingCusId());
                msgReceive1.setStatus(LetterConstans.LETTER_STATUS_READ);
                msgReceive1.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
                msgReceive1.setUpdateTime(new Date());
                msgReceive1.setShowname((user.getShowName()!=null&&!user.getShowName().equals(""))?user.getShowName():user.getEmail());
                msgReceive1.setCusId(0L);
                msgrcList.add(msgReceive1);
            }
            //批量添加站内信
            this.addMsgReceiveBatch(msgrcList);
        }
         
         //查询站内信
        List<QueryMsgReceive> queryMsgReceiveList = msgReceiveDao.queryMsgReceiveByInbox(msgReceive, page);
        //清除粉丝未读消息的缓存
        userService.updateUnReadMsgNumReset("msgNum", msgReceive.getReceivingCusId());
        //上传统计系统消息时间更新最新时间
        userService.updateCusForLST(msgReceive.getReceivingCusId(), new Date());
        return queryMsgReceiveList;
    }

    /**
     * 传来的receivingCusId的用户id 给我发送的站内信的历史记录
     */
    public List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception {
        List<QueryMsgReceive> queryMsgReceiveList = msgReceiveDao.queryMsgReceiveHistory(msgReceive, page);// 传来的receivingCusId的用户id
        Map<String, User> map = userService.getUserExpandByUids(getMsgReceiveListCusId(queryMsgReceiveList));// 批量查询用户的信息
        if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {
            for (QueryMsgReceive queryMsgReceive : queryMsgReceiveList) {
                if (queryMsgReceive.getCusId() == msgReceive.getCusId()) {
                   User userExpandDto = map.get(queryMsgReceive.getCusId() + "");// 查询用户的信息
                    if (userExpandDto != null) {// 如果能够查到则set 头像信息
                        queryMsgReceive.setUserExpandDto(userExpandDto);
                    }
                } else {
                    User userExpandDto = map.get(queryMsgReceive.getCusId() + "");// 查询用户的信息
                    if (userExpandDto != null) {// 如果能够查到则set 头像信息
                        queryMsgReceive.setUserExpandDto(userExpandDto);
                    }
                    User userExpandDto2 = userService.queryUserById(Integer.parseInt(queryMsgReceive.getReceivingCusId().toString()));
                    queryMsgReceive.setShowname(userExpandDto2.getShowName());// set
                    // 发件人的用户名
                }
            }
        }
        return queryMsgReceiveList;// 传来的receivingCusId的用户id
    }

    /**
     * 查询站内信发件箱
     */
    public List<QueryMsgReceive> queryMsgReceiveByOutbox(MsgReceive msgReceive, PageEntity page) throws Exception {
        return msgReceiveDao.queryMsgReceiveByOutbox(msgReceive, page);
    }

    /**
     * 删除站内信
     */
    public Long delMsgReceive(MsgReceive msgReceive) throws Exception {
        return msgReceiveDao.delMsgReceive(msgReceive);
    }

    /**
     * 删除站内信过期消息
     */
    public Long delMsgReceivePast(Date time) throws Exception {
        return msgReceiveDao.delMsgReceivePast(time);
    }

    /**
     * 删除收件箱
     */
    public Long delMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
        return msgReceiveDao.delMsgReceiveInbox(msgReceive);// 更新站内信的状态 删除收件箱
    }

    /**
     * 更新收件箱所有信为已读
     */
    public void updateAllReadMsgReceiveInbox(MsgReceive msgReceive) throws Exception {
        msgReceiveDao.updateAllReadMsgReceiveInbox(msgReceive);// 更新收件箱所有信为已读
    }

    /**
     * 更新发件箱所有信为已读
     */
    public void updateAllReadMsgReceiveOutbox(MsgReceive msgReceive) throws Exception {
        msgReceiveDao.updateAllReadMsgReceiveOutbox(msgReceive);// 更新发件箱所有信为已读
    }

    /**
     * 通过站内信的id更新为已读
     */
    public void updateReadMsgReceiveById(MsgReceive msgReceive) throws Exception {
        msgReceiveDao.updateReadMsgReceiveById(msgReceive);
    }

    /**
     * 查询系统消息
     */
    public List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception {
        msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);// set
        // type为系统消息
        this.updateAllMsgReceiveReadByType(msgReceive);// 更新消息为已读
        return msgReceiveDao.querysystemInform(msgReceive, page);
    }

    public String getMsgReceiveListCusId(List<QueryMsgReceive> queryMsgReceiveList) {// 获得用户ids
        String ids = "";
        if (queryMsgReceiveList != null && queryMsgReceiveList.size() > 0) {
            for (QueryMsgReceive queryMsgReceive : queryMsgReceiveList) {
                ids += queryMsgReceive.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 通过站内信的id更新status
     */
    public void updateStatusReadMsgReceiveById(int status, Long msgReceiveId, Long receivingCusId) throws Exception {
        MsgReceive msgReceive = new MsgReceive();
        msgReceive.setId(msgReceiveId);// set 消息的id
        msgReceive.setStatus(status);// set状态
        msgReceive.setReceivingCusId(receivingCusId);// 当前登陆人的用户id
        msgReceiveDao.updateStatusReadLetterById(msgReceive);// 更新 消息的专题
    }

    /**
     * 根据cusId和receivingCusId 更新状态
     */
    public void updateStatusReadMsgReceiveByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception {
        msgReceiveDao.updateStatusReadLetterByCusIdAndReceivingCusId(status, msgReceive);// 根据cusId和receivingCusId
        // 更新状态
    }

    /**
     * 发送系统消息
     */
    public String addSystemMessageByCusId(String content, Long cusId,String msgType) throws Exception {
        
        User userExpandDto = userService.queryUserById(Integer.parseInt(cusId.toString()));
        
        MsgReceive msgReceive = new MsgReceive();
        msgReceive.setContent(content);// 添加站内信的内容
        msgReceive.setCusId(Long.valueOf(0));
        msgReceive.setReceivingCusId(cusId);// 要发送的用户id
        msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);// 消息未读状态
        int type = 1;
        if ("timeOverCouponCodeMsg".equals(msgType)){
            type = 6;
        }else if ("timeOverMsg".equals(msgType)){
            type = 5;
        }
        msgReceive.setType(type);// 系统消息
        msgReceive.setUpdateTime(new Date());// 更新时间s
        msgReceive.setAddTime(new Date());// 添加时间
        if (userExpandDto != null && userExpandDto.getShowName() != null) {// 如果不为空则set showname
            msgReceive.setShowname(userExpandDto.getShowName());// 会员名
        } else {// 如果为空则set 空字符串
            msgReceive.setShowname("");// 会员名
        }
        try{
        	msgReceiveDao.addMsgReceive(msgReceive);
        	userService.updateUnReadMsgNumAddOne("sysMsgNum", cusId);
        }catch(Exception e){
        	logger.error("addSystemMessageByCusId---send message is error", e);
        }
        
        return "success";
    }

    /**
     * 查询该用户未读消息数量
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     */
    public Map<String, String> queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception {
        @SuppressWarnings("unchecked")
		Map<String,String> map =new HashMap<String,String>();
        User userExpandDto=userService.queryUserById(Integer.parseInt(cusId.toString()));
        //未读系统自动消息数
        int smNum = userExpandDto.getSysMsgNum();
        //未读站内信数
        int mNum = userExpandDto.getMsgNum();
        //上次查询系统消息时间
        Date lastTime = userExpandDto.getLastSystemTime();
        List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);

        map.put("mNum", mNum + "");
        if (ObjectUtils.isNotNull(MSlist)) {
            map.put("SMNum", smNum + MSlist.size() + "");
            map.put("unReadNum", mNum + MSlist.size() + smNum + "");
        } else {
            map.put("SMNum", smNum + "");
            map.put("unReadNum", mNum +  smNum + "");
        }
        return map;// 返回查好的数据
    }

    /**
     * 更新某种类型的站内信状态为已读
     */
    public void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception {
        msgReceiveDao.updateAllMsgReceiveReadByType(msgReceive);// 更新消息为已读
    }

    /**
     * 批量添加消息
     */
    public Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList) {
        return msgReceiveDao.addMsgReceiveBatch(msgReceiveList);
    }

    /**
     * 清空站内信收件箱
     */
    public Long delAllOutbox(Long cusId) throws Exception {
        return msgReceiveDao.delAllOutbox(cusId);
    }

    /**
     * 清空用户系统消息
     */
    public Long delAllMsgSys(Long cusId) throws Exception {
        return msgReceiveDao.delAllMsgSys(cusId);
    }

    /**
     * 删除传入的ids
     */
    public Long delMsgReceiveByids(String ids) throws Exception {
        return msgReceiveDao.delMsgReceiveByids(ids);
    }

    /**
     * 消息发送
     */
    public void sendMessage(int userId, String emailTitle,String msgType,boolean useModel,String... params) throws Exception {
        //获取消息开关配置 默认都能发送
        String configs="letter,email,mobile";
        //消息模板
        String modelContent="";
        User user=userService.queryUserById(userId);
        String email=user.getEmail();
        String mobile=user.getMobile();
        //使用模板的情况下将查询模板后台设置信息
        if(useModel){
            //获取消息下的发送方式和模板
            if(StringUtils.isNotEmpty(msgType)){
                Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.message.toString());
                if (ObjectUtils.isNotNull(keywordmap)) {

                    keywordmap = (Map<String, Object>) keywordmap.get(WebSiteProfileType.message.toString());
                }
                //获取消息下的发送方式和模板
                if(ObjectUtils.isNotNull(keywordmap.get(msgType))){
                    Map<String,Object> msgConfig=(Map<String, Object>)keywordmap.get(msgType);
                    configs=msgConfig.get("sendType").toString();
                    modelContent=msgConfig.get("modelContent").toString();
                }else{
                    configs="";
                    modelContent="";
                }
            }

            //处理填充消息内容
            for(String str : params){
                Pattern p = Pattern.compile("\\{1\\}");
                Matcher m = p.matcher(modelContent);
                modelContent=m.replaceFirst(str);
            }
        }else{
            //不使用模板的情况下内容将显示传过来的参数
            modelContent=params[0];
        }

        //站内信
        if(ObjectUtils.isNotNull(userId) && configs.indexOf("letter")>=0){
            this.addSystemMessageByCusId(modelContent,Long.valueOf(userId) ,msgType);
        }

        //邮箱发送
        if(StringUtils.isNotEmpty(email) && configs.indexOf("email")>=0){
            Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
            String company = web.get("company").toString();
            emailService.sendMail(email,modelContent, emailTitle+"[" + company + "]");
        }

        //短信发送
        if(StringUtils.isNotEmpty(mobile) && configs.indexOf("mobile")>=0){
            /*获取对应的模板信息*/
            Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.message.toString());
            keywordmap = (Map<String, Object>) keywordmap.get(WebSiteProfileType.message.toString());
            Map<String,Object> msgConfig=(Map<String, Object>)keywordmap.get(msgType);
            /*短信签名*/
            String sign = msgConfig.get("sign").toString();
              /* 如果没有选择模板直接返回不继续执行*/
            if(ObjectUtils.isNull(msgConfig.get("template"))){
                return;
            }else if("请选择模板".equals(msgConfig.get("template"))){
                return;
            }
            /*短信模板*/
            String template = msgConfig.get("template").toString();

            try{
                int tpl_id = Integer.parseInt(template);
                SmsUtil smsUtil=new SmsUtil();
                smsUtil.setDestNumber(mobile);
                smsUtil.setTpl_id(tpl_id);
                smsUtil.setSign(sign);
                smsUtil.setMsgContent(params[0]);
                smsUtil.sendmsgPoint();
            }catch (Exception e){

            }

        }
    }

    @Override
    public List<QueryMsgReceive> queryMsgReceiveList(MsgReceive msgReceive) throws Exception {
        //查询用户信息
        User user  = userService.queryUserById((Integer.parseInt(msgReceive.getReceivingCusId().toString())));
        Date lastTime = user.getLastSystemTime();
        //查询系统发送的未读消息
        List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);
        if (ObjectUtils.isNotNull(MSlist)) {
            List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
            //查出未读的系统消息插入到系统中 更新
            for (MsgSystem mgstm : MSlist) {
                MsgReceive msgReceive1 = new MsgReceive();
                msgReceive1.setContent(mgstm.getContent());
                msgReceive1.setAddTime(new Date());
                msgReceive1.setReceivingCusId(msgReceive.getReceivingCusId());
                //msgReceive1.setStatus(LetterConstans.LETTER_STATUS_READ);
                msgReceive1.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
                msgReceive1.setUpdateTime(new Date());
                msgReceive1.setShowname((user.getShowName()!=null&&!user.getShowName().equals(""))?user.getShowName():user.getEmail());
                msgReceive1.setCusId(0L);
                msgrcList.add(msgReceive1);
            }
            //批量添加站内信
            this.addMsgReceiveBatch(msgrcList);
        }

        //查询站内信
        List<QueryMsgReceive> queryMsgReceiveList = msgReceiveDao.queryMsgReceiveList(msgReceive);
        //清除粉丝未读消息的缓存
        userService.updateUnReadMsgNumReset("msgNum", msgReceive.getReceivingCusId());
        //上传统计系统消息时间更新最新时间
        userService.updateCusForLST(msgReceive.getReceivingCusId(), new Date());
        return queryMsgReceiveList;
    }

    public QueryMsgReceive queryMsgReceiveById(Long id){
        return msgReceiveDao.queryMsgReceiveById(id);
    }

}
