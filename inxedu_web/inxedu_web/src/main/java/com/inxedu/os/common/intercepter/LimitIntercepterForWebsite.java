package com.inxedu.os.common.intercepter;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.website.WebsiteImages;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.website.WebsiteImagesService;
import com.inxedu.os.edu.service.website.WebsiteNavigateService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 网站配置管理拦截器
 * @author www.inxedu.com
 */
public class LimitIntercepterForWebsite extends HandlerInterceptorAdapter{
	 //logger
	 Logger logger = LoggerFactory.getLogger(LimitIntercepterForWebsite.class);
     @Autowired
     private WebsiteProfileService websiteProfileService;
 	 @Autowired
 	 private WebsiteNavigateService websiteNavigateService;
 	 @Autowired
 	 private WebsiteImagesService websiteImagesService;
 	 @Autowired
 	 private SubjectService subjectService;

     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         super.afterCompletion(request, response, handler, ex);
     }

     
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         super.postHandle(request, response, handler, modelAndView);
     }
     
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         try{
        	// 获得banner图
 			Map<String, List<WebsiteImages>> websiteImages = websiteImagesService.queryImagesByType();
 			request.setAttribute("websiteImages", websiteImages);
        	//获得网站配置、LOGO配置、网站统计代码、咨询链接
          	Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
          	request.setAttribute("websitemap",websitemap);
            //网站导航配置
          	Map<String,Object> navigatemap=websiteNavigateService.getWebNavigate();
      		request.setAttribute("navigatemap",navigatemap);
             //购买服务开关
             Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
             CacheConstans.ZHIFUBAO_IS_OPEN=serviceSwitch.get("alipay")+"";
             CacheConstans.WEIXIN_IS_OPEN=serviceSwitch.get("weixinpay")+"";
             CacheConstans.YIBAO_IS_OPEN=serviceSwitch.get("yibaopay")+"";
             CacheConstans.COUPON_IS_OPEN=serviceSwitch.get("coupon")+"";
             CacheConstans.QQ_LOGIN_IS_OPEN=serviceSwitch.get("verifyQQ")+"";
             CacheConstans.WEIXIN_LOGIN_IS_OPEN=serviceSwitch.get("verifyWeiXin")+"";
             CacheConstans.SINA_LOGIN_IS_OPEN=serviceSwitch.get("verifySINA")+"";
             CacheConstans.ACCOUNT_IS_OPEN=serviceSwitch.get("account")+"";

             CacheConstans.MEMBER_IS_OPEN=serviceSwitch.get("member")+"";
             request.setAttribute("serviceSwitch", serviceSwitch);
            //短信配置
             Map<String,Object> smsmap = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString()).get(WebSiteProfileType.sms.toString());
             CacheConstans.smsType=String.valueOf(smsmap.get("smstype"));
             CacheConstans.smssdkappid=String.valueOf(smsmap.get("sdkappid"));
             CacheConstans.smsstrAppkey=String.valueOf(smsmap.get("strAppkey"));

             //注册开关配置
             Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
             request.setAttribute("registerController",registerController);

             //网站开关配置
             Map<String,Object> WebSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.WebSwitch.toString()).get(WebSiteProfileType.WebSwitch.toString());
             request.setAttribute("WebSwitch",WebSwitch);

             //提现开关
             Map<String,Object> withdrawals = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.withdrawals.toString()).get(WebSiteProfileType.withdrawals.toString());
             request.setAttribute("withdrawals",withdrawals);

             //判断是否 微信内置浏览器
             String agent=request.getHeader("User-Agent");

             //获得 微信登录 配置
             Map<String,Object> weixinLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("weixinLogin").get("weixinLogin");

             //微信内置浏览器 并且  微信登录开关限制  并且 是微信强制授权 ,传递到前台，用于隐藏退出、登录按钮
             request.setAttribute("weixin_browser_hidden_button",StringUtils.isNotEmpty(agent)&&agent.toLowerCase().indexOf("micromessenger")>-1 && "ON".equals(CacheConstans.WEIXIN_LOGIN_IS_OPEN) && "ON".equals(weixinLogin.get("forceWeChatLogin")));

             //请求路径
             String requestUri=request.getRequestURI();
             User user = SingletonLoginUtils.getLoginUser(request);
             //微信内置浏览器 并且  微信登录开关限制  并且 是微信强制授权  并且 地址不为/front/bundingProfile（绑定页面） 并且 未登录 并且地址不为/api/auth/live（直播 观看 因酷云 直播回调 认证接口）
             if(StringUtils.isNotEmpty(agent)&&agent.toLowerCase().indexOf("micromessenger")>-1 && "ON".equals(CacheConstans.WEIXIN_LOGIN_IS_OPEN) && "ON".equals(weixinLogin.get("forceWeChatLogin")) && !"/front/bundingProfile".equals(requestUri) && user==null
                     && !"/api/auth/live".equals(requestUri)
                     ){
                 //授权登陆页面
                 String APPID=(String) weixinLogin.get("weixinLogin_appId");
                 String REDIRECT_URI= URLEncoder.encode(CommonConstants.contextPath+"/app/ajax/weChatWebpageLogin");
                 String callBack="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
                 response.sendRedirect(callBack);//跳转微信登录页面
                 return false;
             }

             //获取所有的专业
             List<Subject> subjectList =(List<Subject>)CacheUtil.get(CacheConstans.INDEX_ALL_SUBJECT);
             if(ObjectUtils.isNull(subjectList)){
                 // 查询所有1级专业
                 QuerySubject querySubject = new QuerySubject();
                 querySubject.setParentId(0);
                 subjectList = subjectService.getSubjectList(querySubject);
                 for(Subject subject:subjectList){
                     // 查询2级专业
                     subject.setSubjectList(subjectService.getSubjectListByOne(Long.valueOf(subject.getSubjectId())));
                 }
                 CacheUtil.set(CacheConstans.INDEX_ALL_SUBJECT,subjectList,CacheConstans.RECOMMEND_COURSE_TIME);
             }
             request.setAttribute("headerSubjectList",subjectList);

         }catch(Exception e){
        	 logger.error("LimitIntercepterForWebsite.preHandle 网站配置出错",e);
         }
    	return super.preHandle(request, response, handler);
    } 
}
