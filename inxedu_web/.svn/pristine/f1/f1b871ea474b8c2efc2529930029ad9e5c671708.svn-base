package com.inxedu.os.edu.service.mobile;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.util.QCloud.TengxunyunMessageUtil;
import com.inxedu.os.common.util.StringUtils;

/**
 * 
 * @ClassName  com.inxedu.os.edu.service.user.SmsServiceStub
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月22日 下午5:30:39
 */
public class SmsUtil {
    private String msgContent;//短信内容 现传对应的短信动态参数
    private String destNumber;
    private String sign;//短信签名
    private int tpl_id;//腾讯云短信模板的id
    private String count;//验证码

    public String sendmsg(){
        String result = "";
        try {
            //通过腾讯云短信工具类发送短信
            if("tengXunSms".equals(CacheConstans.smsType)){
                TengxunyunMessageUtil tm = new TengxunyunMessageUtil(CacheConstans.smssdkappid,CacheConstans.smsstrAppkey);
                result  = tm.sendmsg(destNumber,msgContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /*批量发送短信 linksmanArr手机号数组 content短信内容 */
    public String sendMsgBatch(String[]linksmanArr, String content){
        String result = "";
        try {
            //通过腾讯云短信工具类发送短信
            if("tengXunSms".equals(CacheConstans.smsType)){
                TengxunyunMessageUtil tm = new TengxunyunMessageUtil(CacheConstans.smssdkappid,CacheConstans.smsstrAppkey);
                result  = tm.sendMsgBatch(linksmanArr,content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String sendmsgPoint(){
        String result = "";
        try {
            //通过腾讯云短信工具类发送短信
            if("tengXunSms".equals(CacheConstans.smsType)){
                TengxunyunMessageUtil tm = new TengxunyunMessageUtil(CacheConstans.smssdkappid,CacheConstans.smsstrAppkey);
                if (StringUtils.isEmpty(msgContent)){
                    System.out.println("手机号："+destNumber+"-----验证码："+count);
                    result = tm.sendmsgPoint(destNumber,sign,tpl_id,count);
                }else if (StringUtils.isEmpty(count)) {
                    result = tm.sendmsgPoint(destNumber,sign,tpl_id,msgContent);
                }else {
                    result = tm.sendmsgPoint(destNumber,sign,tpl_id,msgContent,count);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public String getDestNumber() {
        return destNumber;
    }
    public void setDestNumber(String destNumber) {
        this.destNumber = destNumber;
    }

    public String getSign() {
        return sign;
    }

    public int getTpl_id() {
        return tpl_id;
    }

    public String getCount() {
        return count;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTpl_id(int tpl_id) {
        this.tpl_id = tpl_id;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
