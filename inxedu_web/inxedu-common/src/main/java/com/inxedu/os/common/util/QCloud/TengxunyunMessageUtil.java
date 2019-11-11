package com.inxedu.os.common.util.QCloud;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 腾讯云短信工具类
 */
public class TengxunyunMessageUtil {

    private String sdkappid = "";
    private String strAppkey = "";//sdkappid对应的appkey，需要业务方高度保密
    public TengxunyunMessageUtil(String sdkappid,String strAppkey){
        this.sdkappid = sdkappid;
        this.strAppkey = strAppkey;
    }
    public TengxunyunMessageUtil(){
    }
    /**
    * 发送短信
     * */
    public String sendmsg(String destNumber,String msgContent){
        String result = "";
        try {

            String random = (int)(1+Math.random()*(100-1+1))+"";
            String url="https://yun.tim.qq.com/v3/tlssmssvr/sendsms?sdkappid="+sdkappid+"&random="+random;
            url=url+"&content="+URLEncoder.encode(msgContent,"UTF-8");
            result = QCloudUtil.sendPost(url,sendmsgparam(destNumber,msgContent));
            //把返回的json数据转化为Map
            Map<String,Object> map1= FastJsonUtil.json2Map(result);
            result =  msgReturnError(map1);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 发送指定模板短信
     * */
    public String sendmsgPoint(String destNumber,String sign,int tpl_id,String...params){
        String result="";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            String url="https://yun.tim.qq.com/v3/tlssmssvr/sendsms?sdkappid="+sdkappid+"&random="+random;
            result = QCloudUtil.sendPost(url,sendmsgparamPoint(destNumber,sign,tpl_id,params));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(TengxunyunMessageUtil.class);
    /**
     * 批量发送短信
     * */
    public String sendMsgBatch(String[] destNumber, String msgContent){
        String error = "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            String url="https://yun.tim.qq.com/v3/tlssmssvr/sendmultisms2?sdkappid="+sdkappid+"&random="+random;
            url=url+"&content="+URLEncoder.encode(msgContent,"UTF-8");
            /*短信返回的json包*/
            String returnMsg = QCloudUtil.sendPost(url,sendmsgparamBatch(destNumber,msgContent));
            //把json数据转化为Map
            Map<String,Object> map1=FastJsonUtil.json2Map(returnMsg);
            /*获取错误提示*/
            error = msgReturnError(map1);
            System.out.println("destNumber:"+destNumber);
            System.out.println("msgContent:"+msgContent);
            logger.error("sendMsgBatch", error);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }
    /*腾讯云短信错误码对应的错误*/
    public String msgReturnError(Map<String,Object> map1){
        String error="";
        BigDecimal num = new BigDecimal(map1.get("result").toString());
        int errorNum = num.intValue();
        if (errorNum!=0){
            if (errorNum==1001){
                error =  "AppKey错误";
            }else if (errorNum==1002){
                error =  "短信内容中含有脏字";
            }else if (errorNum==1003){
                error =  "未填AppKey ";
            }else if (errorNum==1004){
                error =  "REST API请求参数有误";
            }else if (errorNum==1006){
                error =  "没有权限 ";
            }else if (errorNum==1007){
                error =  "其他错误 ";
            }else if (errorNum==1008){
                error =  "下发短信超时";
            }else if (errorNum==1009){
                error =  "用户IP不在白名单中 ";
            }else if (errorNum==1011){
                error =  "REST API命令字错误";
            }else if (errorNum==1012){
                error =  "短信内容格式错误";
            }else if (errorNum==1013){
                error =  "下发短信频率限制";
            }else if (errorNum==1014){
                error =  "模版未审批";
            }else if (errorNum==1015){
                error =  "黑名单手机";
            }else if (errorNum==1016){
                error =  "错误的手机号格式";
            }else if (errorNum==1017){
                error =  "短信内容过长";
            }else if (errorNum==1018){
                error =  "语音验证码格式错误 ";
            }else if (errorNum==1019){
                error =  "sdkappid不存在";
            }else if (errorNum==1020){
                error =  "sdkappid已禁用";
            }else if (errorNum==1021){
                error =  "请求发起时间不正常";
            }else {
                error = "未知错误";
            }
        }
        return error;
    }

    public static void main(String[] args) {
        /*TengxunyunMessageUtil smsUtil = new TengxunyunMessageUtil();
        //smsServiceStub.sendmsg("13161090129","你的验证码是8888");
        String[] nums = {"13161090129","18201604788"};
        smsUtil.sendMsgBatch(nums,"8888为您的验证码");*/
        TengxunyunMessageUtil smsUtil = new TengxunyunMessageUtil("1400012891","1041c7b04fcf3ae6aeb291b60786999a");

    }

    //发送短信数据整理
    public String sendmsgparamPoint(String destNumber,String sign,int tpl_id,String...params){
        if(StringUtils.isEmpty(destNumber)){
            return "";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> tel = new HashMap<String,Object>();
        tel.put("nationcode","86");
        tel.put("phone",""+destNumber);
        map.put("type","0");//0:普通短信;1:营销短信
        map.put("sign",sign);//短信签名
        map.put("tpl_id",tpl_id);//模板ID
        String[]arr = params;
        map.put("params",params);

        String strPhone = destNumber;
        String sig = MD5.getMD5(strAppkey + strPhone);//将strAppkey和 strPhone拼接后再求md5
        map.put("sig",sig);//app凭证
        map.put("extend","");
        map.put("ext","");
        map.put("tel",tel);
        return FastJsonUtil.obj2JsonString(map);
    }
    //发送短信数据整理
    public String sendmsgparam(String destNumber,String msgContent){
        if(StringUtils.isEmpty(destNumber)|| StringUtils.isEmpty(msgContent)){
            return "";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> tel = new HashMap<String,Object>();
        tel.put("nationcode","86");
        tel.put("phone",""+destNumber);
        map.put("type","0");//0:普通短信;1:营销短信
        map.put("msg",msgContent);
        String strPhone = destNumber;
        String sig = MD5.getMD5(strAppkey + strPhone);//将strAppkey和 strPhone拼接后再求md5
        map.put("sig",sig);//app凭证
        map.put("extend","");
        map.put("ext","");
        map.put("tel",tel);
        return FastJsonUtil.obj2JsonString(map);
    }
    //批量发送短信数据整理
    public String sendmsgparamBatch(String[] destNumber,String msgContent){
        if(ObjectUtils.isNull(destNumber)|| StringUtils.isEmpty(msgContent)){
            return "";
        }
        String phoneStrs = "";
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map> telList = new ArrayList<Map>();
        for(String num :destNumber){
            Map<String,Object> tel = new HashMap<String,Object>();
            tel.put("nationcode","86");
            tel.put("phone",""+num);
            telList.add(tel);
            phoneStrs+=num+",";
        }
        map.put("type","0");//0:普通短信;1:营销短信
        map.put("msg",msgContent);
        phoneStrs = phoneStrs.substring(0,phoneStrs.length()-1);
        String sig = MD5.getMD5(strAppkey + phoneStrs);//将strAppkey和 strPhone拼接后再求md5
        map.put("sig",sig);//app凭证
        map.put("extend","");
        map.put("ext","");
        map.put("tel",telList);
        return FastJsonUtil.obj2JsonString(map);
    }
}
