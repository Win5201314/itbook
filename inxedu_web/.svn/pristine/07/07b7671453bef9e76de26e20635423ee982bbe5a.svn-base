package com.inxedu.os.edu.service.mobile;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.net.URLEncoder;

/**
 * 
 * @ClassName  com.inxedu.os.edu.service.user.SmsServiceStub
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月22日 下午5:30:39
 */
public class HttpMessageUtil {

    private String userId = "";
    private String PASSWORD = "";

    public void sendmsg(String destNumber,String msgContent){
        try {
            HttpClient client = new HttpClient();
            String url="http://138.u59e.com/index.php?action=interface&op=sendmess";
            url=url+"&username="+userId;
            url=url+"&userpwd="+PASSWORD;
            url=url+"&logid=";
            url=url+"&mobiles="+destNumber;
            url=url+"&content="+URLEncoder.encode(msgContent,"UTF-8");
            //要提交的短信内容，中文内容要使用UTF-8字符集进行URL编码，避免有特殊符号造成提交失败
            //例如c# 用HttpUtility.UrlEncode("发送内容",Encoding.UTF8) ，java用URLEncoder.encode("发送内容", "UTF-8")
            PostMethod method = new PostMethod(url);
            client.executeMethod(method);
            method.getResponseBodyAsStream();

            method.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
