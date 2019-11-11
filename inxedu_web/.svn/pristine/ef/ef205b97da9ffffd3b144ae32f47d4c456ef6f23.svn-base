package com.inxedu.os.common.util.QCloud;


import com.google.gson.GsonBuilder;
import com.inxedu.os.common.util.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 腾讯云短信工具类
 */
public class QCloudSMSSignUtil {


    private String sdkappid = "";
    private String strAppkey = "";//sdkappid对应的appkey，需要业务方高度保密
    public QCloudSMSSignUtil(String sdkappid, String strAppkey){
        this.sdkappid = sdkappid;
        this.strAppkey = strAppkey;
    }
    public QCloudSMSSignUtil(){
    }
    /**
     * 添加短信签名
     * */
    public String addSign(String text){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/add_sign?sdkappid="+sdkappid+"&random="+random;
            Map map = new HashMap();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("text",text);
            resultstr= QCloudUtil.sendPost(url, FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
            return resultstr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 短信签名状态查询
     * */
    public String querySign(int[] sign_id){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/get_sign?sdkappid="+sdkappid+"&random="+random;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("sign_id",sign_id);
            resultstr= QCloudUtil.sendPost(url,FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 删除短信签名
     * */
    public String delSign(int sign_id){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/del_sign?sdkappid="+sdkappid+"&random="+random;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("sign_id",sign_id);
            resultstr= QCloudUtil.sendPost(url,FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }


    public static void main(String[] args) {
        QCloudSMSSignUtil smsUtil = new QCloudSMSSignUtil("1400012891","1041c7b04fcf3ae6aeb291b60786999a");
        String str = "{\"result\":0,\"msg\":\"\",\"count\":1,\"data\":[{\"id\":792,\"text\":\",\"status\":0}]}";
        String str2 = "";
        str2 = str.substring(0,str.lastIndexOf(","));
        System.out.println(str2);
        String str3 = str.substring(str2.lastIndexOf(","),str.lastIndexOf(","));
        System.out.println(str3);
        str = str.replaceAll(str3,"");
        System.out.println(str);
    }
}
