package com.inxedu.os.edu.util.qq;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.qq.rewrite.QQConnect;
import com.qq.connect.QQConnectException;
import com.qq.connect.utils.http.PostParameter;

import java.util.Map;

public class InxeduUserInfo extends QQConnect {

    public InxeduUserInfo(){}

    private WebsiteProfileService websiteProfileService;


    private static final long serialVersionUID = -6124397423510235640L;

    public InxeduUserInfo(String token, String openID,WebsiteProfileService websiteProfileService) {
        super(token, openID);
        this.websiteProfileService=websiteProfileService;
    }

    private InxeduUserInfoBean getUserInfo(String openid) throws QQConnectException {
        //获得 qq 登录 配置
        Map<String,Object> qqLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("qqLogin").get("qqLogin");

        return new InxeduUserInfoBean(this.client.get(qqLogin.get("getUserInfoURL").toString(), new PostParameter[]{new PostParameter("openid", openid), new PostParameter("oauth_consumer_key",
                qqLogin.get("app_ID").toString()), new PostParameter("access_token", this.client.getToken()), new PostParameter("format", "json")}).asJSONObject());
    }

    public InxeduUserInfoBean getUserInfo() throws QQConnectException {
        return this.getUserInfo(this.client.getOpenID());
    }
}
