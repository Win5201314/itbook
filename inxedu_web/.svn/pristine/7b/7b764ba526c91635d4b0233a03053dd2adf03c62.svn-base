package com.inxedu.os.common.util.QCloud;


import com.google.gson.GsonBuilder;
import com.inxedu.os.common.util.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 腾讯云短信工具类
 */
public class QCloudSMSTemplateUtil {

    private String sdkappid = "";
    private String strAppkey = "";//sdkappid对应的appkey，需要业务方高度保密
    public QCloudSMSTemplateUtil(String sdkappid, String strAppkey){
        this.sdkappid = sdkappid;
        this.strAppkey = strAppkey;
    }
    public QCloudSMSTemplateUtil(){
    }
    /**
     * 添加短信模板
     * */
    public String addTemplate(String text,int type){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/add_template?sdkappid="+sdkappid+"&random="+random;
            Map map = new HashMap();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("text",text);//模板内容
            map.put("type",type);//0营销短信模板，1普通短信模板
            resultstr= QCloudUtil.sendPost(url,FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
            return resultstr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 短信模板状态查询
     * */
    public String queryTemplate(int[] tpl_id){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/get_template?sdkappid="+sdkappid+"&random="+random;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("tpl_id",tpl_id);
            resultstr= QCloudUtil.sendPost(url, FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 删除短信模板
     * */
    public String delTemplate(int[] tpl_id){
        String resultstr= "";
        try {
            String random = (int)(1+Math.random()*(100-1+1))+"";
            long nowdate = QCloudUtil.unixtime();
            String url="https://yun.tim.qq.com/v3/tlssmssvr/del_template?sdkappid="+sdkappid+"&random="+random;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("rand",random);
            map.put("time",nowdate);
            String sig = QCloudUtil.Encrypt("appkey="+strAppkey+"&rand="+random+"&time="+nowdate,"SHA-256");
            map.put("sig",sig);
            map.put("tpl_id",tpl_id);
            resultstr= QCloudUtil.sendPost(url,FastJsonUtil.obj2JsonString(map));
            System.out.println(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

}
