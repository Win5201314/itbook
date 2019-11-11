package com.inxedu.os.edu.util.qq.rewrite;

import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.qq.connect.QQConnectException;
import com.qq.connect.utils.http.PostParameter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenID extends QQConnect {
    private static final long serialVersionUID = 6913005509508673584L;
    private WebsiteProfileService websiteProfileService;

    public OpenID(String token,WebsiteProfileService websiteProfileService) {
        this.client.setToken(token);
        this.websiteProfileService=websiteProfileService;
    }

    private String getUserOpenID(String accessToken) throws QQConnectException {
        //获得 qq 登录 配置
        Map<String,Object> qqLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("qqLogin").get("qqLogin");

        String openid = "";
        String jsonp = this.client.get(qqLogin.get("getOpenIDURL").toString(), new PostParameter[]{new PostParameter("access_token", accessToken)}).asString();
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(jsonp);
        if(m.find()) {
            openid = m.group(1);
            return openid;
        } else {
            throw new QQConnectException("server error!");
        }
    }

    public String getUserOpenID() throws QQConnectException {
        String accessToken = this.client.getToken();
        return this.getUserOpenID(accessToken);
    }
}

