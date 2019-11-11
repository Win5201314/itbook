package com.inxedu.os.edu.util.qq;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.qq.rewrite.AccessToken;
import com.inxedu.os.edu.util.qq.rewrite.QQConnect;
import com.qq.connect.QQConnectException;
import com.qq.connect.utils.RandomStatusGenerator;
import com.qq.connect.utils.http.PostParameter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author www.inxedu.com
 */
public class InxeduQqUtils extends QQConnect {

    public InxeduQqUtils(){

    }

    private WebsiteProfileService websiteProfileService;

    public InxeduQqUtils(WebsiteProfileService websiteProfileService){
        this.websiteProfileService=websiteProfileService;
    }





    public String getAuthorizeURL(ServletRequest request) throws QQConnectException {


        //获得 qq 登录 配置
        Map<String,Object> qqLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("qqLogin").get("qqLogin");

        String state = RandomStatusGenerator.getUniqueState();
        ((HttpServletRequest)request).getSession().setAttribute("qq_connect_state", state);
        String scope = qqLogin.get("scope").toString();
        return scope != null && !scope.equals("")?getAuthorizeURL("code", state, scope):qqLogin.get("authorizeURL").toString().trim() + "?client_id=" +
                qqLogin.get("app_ID").toString().trim() + "&redirect_uri=" + CommonConstants.contextPath+"/app/loginReturn" + "&response_type=" + "code" + "&state=" + state;
    }


    public String getAuthorizeURL(String response_type, String state, String scope) throws QQConnectException {
        //获得 qq 登录 配置
        Map<String,Object> qqLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("qqLogin").get("qqLogin");

        return qqLogin.get("authorizeURL").toString().trim() + "?client_id=" + qqLogin.get("app_ID").toString().trim() + "&redirect_uri=" + CommonConstants.contextPath+"/app/loginReturn" + "&response_type=" + response_type + "&state=" + state + "&scope=" + scope;
    }


    private String[] extractionAuthCodeFromUrl(String url) throws QQConnectException {
        if(url == null) {
            throw new QQConnectException("you pass a null String object");
        } else {
            Matcher m = Pattern.compile("code=(\\w+)&state=(\\w+)&?").matcher(url);
            String authCode = "";
            String state = "";
            if(m.find()) {
                authCode = m.group(1);
                state = m.group(2);
            }

            return new String[]{authCode, state};
        }
    }

    public AccessToken getAccessTokenByRequest(ServletRequest request) throws QQConnectException {
        //获得 qq 登录 配置
        Map<String,Object> qqLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("qqLogin").get("qqLogin");

        String queryString = ((HttpServletRequest)request).getQueryString();
        if(queryString == null) {
            return new AccessToken();
        } else {
            String state = (String)((HttpServletRequest)request).getSession().getAttribute("qq_connect_state");
            if(state != null && !state.equals("")) {
                String[] authCodeAndState = this.extractionAuthCodeFromUrl(queryString);
                String returnState = authCodeAndState[1];
                String returnAuthCode = authCodeAndState[0];
                AccessToken accessTokenObj = null;
                if(!returnState.equals("") && !returnAuthCode.equals("")) {
                    if(!state.equals(returnState)) {
                        accessTokenObj = new AccessToken();
                    } else {
                        accessTokenObj = new AccessToken(this.client.post(qqLogin.get("accessTokenURL").toString(), new PostParameter[]{new PostParameter("client_id", qqLogin.get("app_ID").toString()),
                                new PostParameter("client_secret", qqLogin.get("app_KEY").toString()), new PostParameter("grant_type", "authorization_code"), new PostParameter("code", returnAuthCode),
                                new PostParameter("redirect_uri", CommonConstants.contextPath+"/app/loginReturn")}, Boolean.valueOf(false)));
                    }
                } else {
                    accessTokenObj = new AccessToken();
                }

                return accessTokenObj;
            } else {
                return new AccessToken();
            }
        }
    }
}
