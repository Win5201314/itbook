package com.inxedu.os.edu.controller.thirdpartlogin;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.ProfileType;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.userprofile.UserProfileService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.qq.InxeduQqUtils;
import com.inxedu.os.edu.util.qq.InxeduUserInfo;
import com.inxedu.os.edu.util.qq.InxeduUserInfoBean;
import com.inxedu.os.edu.util.qq.rewrite.AccessToken;
import com.inxedu.os.edu.util.qq.rewrite.OpenID;
import com.qq.connect.QQConnectException;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import weibo4j.Users;
import weibo4j.model.PostParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static com.inxedu.os.common.util.SingletonLoginUtils.getLoginUserId;

@Controller
public class ThirdPartLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPartLoginController.class);

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private UserService userService;

    @InitBinder("userForm")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userForm.");
    }


    /**
     * 跳转到登录连接，返回联合登录的地址 根据传的type处理不同的请求。
     *
     * @param request
     * @param response
     * @param appType
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/app/authlogin")
    public String authlogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String appType, RedirectAttributes redirectAttributes) {
        String callBack = "";
        try {
            // 检查类型并转为统一的大写格式
            appType = checkType(appType);
            if (StringUtils.isEmpty(appType)) {
                return "redirect:/";
            }
            // QQ联合登录的返回处理
            if (ProfileType.QQ.toString().equalsIgnoreCase(appType)) {
                //qq登录开关限制
                if ("OFF".equals(CacheConstans.QQ_LOGIN_IS_OPEN)) {
                    redirectAttributes.addAttribute("msg", "暂不允许QQ账号登录！");
                    return getViewPath("/web/pay/visit-limit");
                }
                // 获取QQ联合登录的地址
                callBack = new InxeduQqUtils(websiteProfileService).getAuthorizeURL(request);
                logger.info("++ QQ callBack:" + callBack);
                return "redirect:" + callBack + "&userForm=1";
            }
            if (ProfileType.SINA.toString().equalsIgnoreCase(appType)) {
                // 获取微博联合登录的地址
                try {
                    //微博登录开关限制
                    if ("OFF".equals(CacheConstans.SINA_LOGIN_IS_OPEN)){
                        redirectAttributes.addAttribute("msg", "暂不允许微博账号登录！");
                        return getViewPath("/web/pay/visit-limit");
                    }
                    //获得 sina 登录 配置
                    Map<String,Object> sinaLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("sinaLogin").get("sinaLogin");

                    String weiboKey = sinaLogin.get("client_ID").toString();
                    String weiboSecret = sinaLogin.get("client_SERCRET").toString();
                    callBack = sinaLogin.get("authorizeURL").toString().trim() + "?client_id=" + sinaLogin.get("client_ID").toString().trim() + "&redirect_uri=" +CommonConstants.contextPath+"/app/sinalogin" + "&response_type=" + "code" + "&state=" + weiboKey + "&scope=" + weiboSecret;

                    logger.info("++ SINA callBack:" + callBack);
                    return "redirect:" + callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the sina access token.");
                }
            }
            //微信联合登录
            if (ProfileType.WEIXIN.toString().equalsIgnoreCase(appType)) {
                try {
                    //微信登录开关限制
                    if ("OFF".equals(CacheConstans.WEIXIN_LOGIN_IS_OPEN)){
                        redirectAttributes.addAttribute("msg", "暂不允许微信账号登录！");
                        return getViewPath("/web/pay/visit-limit");
                    }

                    //获得 微信登录 配置
                    Map<String,Object> weixinLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("weixinLogin").get("weixinLogin");

                    //微信内置浏览器
                    String agent=request.getHeader("User-Agent");
                    if(agent.toLowerCase().indexOf("micromessenger")>-1){
                        //授权登陆页面
                        String APPID=(String) weixinLogin.get("weixinLogin_appId");
                        String REDIRECT_URI= URLEncoder.encode(CommonConstants.contextPath+"/app/ajax/weChatWebpageLogin");
                        callBack="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
                        return "redirect:" + callBack;
                    }


                    callBack += "https://open.weixin.qq.com/connect/qrconnect?appid=" + weixinLogin.get("appid");
                    callBack += "&redirect_uri=" + URLEncoder.encode(CommonConstants.contextPath+"/app/weixinlogin", "utf-8");
                    callBack += "&response_type=" + weixinLogin.get("response_type") + "&scope=" + weixinLogin.get("scope") + "&state=";
                    logger.info("++ WEIXIN callBack:" + callBack);
                    return "redirect:" + callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the weixin access token.");
                }
            }
        } catch (QQConnectException e) {
            logger.error("+++authlogin：get unio login url error:", e);
            return "login";
        }
        return "success";
    }



    /**
     * QQ联合登录成功回调地址
     */
    @RequestMapping("/app/loginReturn")
    public String loginReturn(HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            AccessToken accessTokenObj = (new InxeduQqUtils(websiteProfileService)).getAccessTokenByRequest(request);
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.info("+++loginRetrun，获取返回的参数失败:");
                return "login";
            } else {
                String accessToken = accessTokenObj.getAccessToken();
                long tokenExpirein = accessTokenObj.getExpireIn();
                logger.info("+++accessToken:" + accessToken + ",tokenExpirein:" + tokenExpirein);
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken,websiteProfileService);
                String appId = openIDObj.getUserOpenID();
                logger.info("+++ loginReturn openIDObj:" + appId);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                InxeduUserInfo qzoneUserInfo = new InxeduUserInfo(accessToken, appId,websiteProfileService);
                InxeduUserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(ProfileType.QQ.toString());
                if (userInfoBean != null) {
                    userProfile.setAvatar(userInfoBean.getFigureurl_qq_2());
                    userProfile.setName(userInfoBean.getNickname().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+",""));
                }
                //执行第三方登陆方法
                String url = userProfileService.queryDoUserThreeLogin(userProfile,request,response,redirectAttributes);
                return url;

            }
        } catch (Exception e) {
            logger.error("++++ loginReturn exception", e);
            return "redirect:/";
        }

    }

    /**
     * sina联合登录成功回调地址
     */
    @RequestMapping("/app/sinalogin")
    public String sinaloginReturn(HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            logger.info("+++sinaloginReturn sina invoce:");
            // 根据code获得授权
            String code = request.getParameter("code");

            //获得 sina 登录 配置
            Map<String,Object> sinaLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("sinaLogin").get("sinaLogin");
            weibo4j.http.HttpClient client = new weibo4j.http.HttpClient();
            weibo4j.http.AccessToken accessToken = new weibo4j.http.AccessToken(client.post(sinaLogin.get("accessTokenURL").toString(),
                    new PostParameter[]{new PostParameter("client_id", sinaLogin.get("client_ID").toString()), new PostParameter("client_secret", sinaLogin.get("client_SERCRET").toString()),
                            new PostParameter("grant_type", "authorization_code"), new PostParameter("code", code), new PostParameter("redirect_uri", CommonConstants.contextPath+"/app/sinalogin")}, Boolean.valueOf(false)));


            if (accessToken == null) {
                logger.info("+++sinaloginReturn,gettoken null");
                return "redirect:/";
            } else {
                Users um = new Users();
                um.client.setToken(accessToken.getAccessToken());
                weibo4j.model.User user = um.showUserById(accessToken.getUid());
                logger.info("++++sinaloginReturn token:" + accessToken.toString());
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(accessToken.getUid());
                userProfile.setProfiletype(ProfileType.SINA.toString());

                if (user != null) {
                    logger.info("+++ sinauser name:" + user.getAvatarLarge());
                    //保存头像到服务器
                    userProfile.setName(user.getScreenName().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+",""));
                    userProfile.setAvatar(user.getAvatarLarge());
                }
                //执行第三方登陆方法
                String url = userProfileService.queryDoUserThreeLogin(userProfile,request,response,redirectAttributes);
                return url;

            }
        } catch (Exception e) {
            logger.error("++++ sinaloginReturn exception", e);
            return "redirect:/";
        }
    }

    /**
     * 微信登录回调(获取code参数)
     */
    @RequestMapping("/app/weixinlogin")
    public String weixinloginReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String code = request.getParameter("code");
            if (StringUtils.isNotEmpty(code)) {
                //获得 微信登录 配置
                Map<String,Object> weixinLogin=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("weixinLogin").get("weixinLogin");

                //拼写微信获取access_token访问地址
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
                url += "?appid=" + weixinLogin.get("appid");
                url += "&secret=" + weixinLogin.get("secret");
                url += "&code=" + code;
                url += "&grant_type=authorization_code";

                System.out.println(url+"\n\n");
                //获取返回数据
                JSONObject userMap = urlHalder(url);

                //获取openid
                String openId = userMap.get("openid").toString();
                String accessToken = userMap.get("access_token").toString();
                if (StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(accessToken)) {
                    String infourl = "https://api.weixin.qq.com/sns/userinfo";
                    infourl += "?access_token=" + accessToken;
                    infourl += "&openid=" + openId;
                    //获取返回数据
                    JSONObject userInfoMap = urlHalder(infourl);

                    UserProfile userProfile = new UserProfile();
                    userProfile.setValue(userInfoMap.get("unionid")+"");
                    userProfile.setProfiletype(ProfileType.WEIXIN.toString());
                    userProfile.setAvatar(userInfoMap.get("headimgurl") + "");
                    userProfile.setName(userInfoMap.get("nickname") + "");
                    //执行第三方登陆方法
                    return userProfileService.queryDoUserThreeLogin(userProfile,request,response,redirectAttributes);
                } else {
                    logger.info("++weixinloginReturn，获取返回的参数失败");
                    return "redirect:/";
                }
            } else {
                logger.info("++weixinloginReturn，获取返回的参数失败");
                return "redirect:/";
            }
        } catch (Exception e) {
            logger.error("++++ weixinloginReturn exception", e);
            return "redirect:/";
        }
    }

    /**
     * 微信公众号 微信自带网页登录回调(获取code参数)
     */
    @RequestMapping("/app/ajax/weChatWebpageLogin")
    public String weChatWebpageLoginReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String code = request.getParameter("code");
            if (StringUtils.isNotEmpty(code)) {
                //获得 微信公众号 配置
                Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixinLogin");
                Map<String, Object> weixinMapVal=(Map<String, Object>) weixinMap.get("weixinLogin");
                String APPID=(String) weixinMapVal.get("weixinLogin_appId");
                String wxAppSecret=(String) weixinMapVal.get("weixinLogin_wxAppSecret");

                //拼写微信获取access_token访问地址
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
                url += "?appid=" + APPID;
                url += "&secret=" + wxAppSecret;
                url += "&code=" + code;
                url += "&grant_type=authorization_code";

                //获取返回数据
                JSONObject userMap = urlHalder(url);

                //获取openid
                String openId = userMap.get("openid").toString();
                String accessToken = userMap.get("access_token").toString();
                if (StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(accessToken)) {
                    String infourl = "https://api.weixin.qq.com/sns/userinfo";
                    infourl += "?access_token=" + accessToken;
                    infourl += "&openid=" + openId;
                    //获取返回数据
                    JSONObject userInfoMap = urlHalder(infourl);
                    UserProfile userProfile = new UserProfile();
                    userProfile.setValue(userInfoMap.get("unionid")+"");
                    userProfile.setValueTwo(userInfoMap.get("openid")+"");//微信公众平台 openId
                    userProfile.setProfiletype(ProfileType.WEIXIN.toString());
                    userProfile.setName(userInfoMap.get("nickname") + "");
                    userProfile.setAvatar(userInfoMap.get("headimgurl") + "");
                    //执行第三方登陆方法
                    return userProfileService.queryDoUserThreeLogin(userProfile,request,response,redirectAttributes);
                } else {
                    logger.info("++weixinloginReturn，获取返回的参数失败");
                    return "redirect:/";
                }
            } else {
                logger.info("++weixinloginReturn，获取返回的参数失败");
                return "redirect:/";
            }
        } catch (Exception e) {
            logger.error("++++ weixinloginReturn exception", e);
            return "redirect:/";
        }
    }

    /**
     * 处理微信访问的url
     */
    private JSONObject urlHalder(String url) throws Exception {
        //请求访问
        HttpClient client = new HttpClient();
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        String result = method.getResponseBodyAsString();
        method.releaseConnection();
        JSONObject jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }

    /**
     * 检查登录类型是否符合
     */
    public String checkType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        if (ProfileType.QQ.toString().equalsIgnoreCase(type)) {
            type = ProfileType.QQ.toString();
        }
        if (ProfileType.SINA.toString().equalsIgnoreCase(type)) {
            type = ProfileType.SINA.toString();
        }
        return type;
    }
    /**
     * 初始化修改用户信息页面
     */
    @RequestMapping("/uc/threelogin")
    public String threelogin(HttpServletRequest request){
        try{
            List<UserProfile> userProfileList = userProfileService.getUserProfileByUserId(getLoginUserId(request));
            //QQ第三方信息
            UserProfile userProfileQQ = new UserProfile();
            UserProfile userProfileSina = new UserProfile();
            UserProfile userProfileWeiXin = new UserProfile();

            //循环查出第三方信息
            for (UserProfile userProfile : userProfileList) {
                if (userProfile.getProfiletype().equals(ProfileType.QQ.toString())) {
                    //QQ
                    userProfileQQ = userProfile;
                }
                if (userProfile.getProfiletype().equals(ProfileType.SINA.toString())) {
                    //新浪
                    userProfileSina = userProfile;
                }
                if (userProfile.getProfiletype().equals(ProfileType.WEIXIN.toString())) {
                    //微信
                    userProfileWeiXin = userProfile;
                }
            }

            request.setAttribute("QQ_LOGIN_IS_OPEN", CacheConstans.QQ_LOGIN_IS_OPEN);
            request.setAttribute("SINA_LOGIN_IS_OPEN", CacheConstans.SINA_LOGIN_IS_OPEN);
            request.setAttribute("WEIXIN_LOGIN_IS_OPEN", CacheConstans.WEIXIN_LOGIN_IS_OPEN);

            request.setAttribute("userProfileQQ", userProfileQQ);
            request.setAttribute("userProfileSina", userProfileSina);
            request.setAttribute("userProfileWeiXin", userProfileWeiXin);
            //微信内置浏览器
            String agent=request.getHeader("User-Agent");
            request.setAttribute("isWebchatPage", agent.toLowerCase().indexOf("micromessenger") > -1);
        }catch (Exception e) {
            logger.error("threelogin()---error",e);
            return this.setExceptionRequest(request, e);
        }
        return getViewPath("/web/ucenter/three-login");
    }

    /**
     * 第三方绑定解绑操作
     */
    @RequestMapping("/uc/ajax/removeBinding")
    @ResponseBody
    public Map<String, Object> excludeBunging(@RequestParam("id") int id) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(id)) {
                userProfileService.deleteUserProfileById(id);
                json = setJson(true, "解绑成功！", null);
            } else {
                json = setJson(false, "系统繁忙,请稍后再试", null);
            }
        } catch (Exception e) {
            logger.error("OpenappWebController.excludeBunging()--error", e);
            json = setJson(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }

    /**
     * 第三方关联
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/front/bundingProfile")
    public String bundingProfile(HttpServletRequest request,
                                 @ModelAttribute("userProfileJson") String userProfile) {
        try {
            if (SingletonLoginUtils.getLoginUserId(request)>0) {
                return "redirect:/uc/index";
            }
            //接收第三方用户信息
            if (StringUtils.isNotEmpty(userProfile)) {
                Map<String, String> userProfileMap = FastJsonUtil.json2StringMap(userProfile);
                request.setAttribute("userProfile", userProfileMap);
            }
        } catch (Exception e) {
            logger.error("UserController.bundingProfile()---error", e);
            setExceptionRequest(request, e);
        }
        return getViewPath("/web/user/bunding_profile");
    }

    /**
     * 第三方绑定已存在的账号(相当于登录操作)
     * @param request
     * @param response
     * @param account       账户
     * @param password    密码
     * @param userProfileId 第三方账号userProfileId
     * @return
     */
    @RequestMapping("/front/ajax/bundingOld")
    @ResponseBody
    public Map<String, Object> bundingOld(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam("account") String account,
                                        @RequestParam("password") String password,
                                        @RequestParam("userProfileId") Long userProfileId) {
        Map<String, Object> json = null;
        try {
            UserProfile queryUserProfile = userProfileService.queryUserProfileById(userProfileId);
            //验证第三方参数
            if (ObjectUtils.isNull(queryUserProfile)) {
                json = this.setJson(false, "参数错误！", null);
                return json;
            }

            if(!StringUtils.isNotEmpty(account)){
                json = this.setJson(false, "请输入登录帐号", null);
                return json;
            }
            if(!StringUtils.isNotEmpty(password)){
                json = this.setJson(false, "请输入登录密码", null);
                return json;
            }

            User user = userService.getLoginUser(account, MD5.getMD5(password));

            if(user==null){
                json = this.setJson(false, "帐号或密码错误", null);
                return json;
            }
            if(user.getIsavalible()==2){
                json = this.setJson(false, "帐号已被禁用", null);
                return json;
            }

            String prefix = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
            if(prefix!=null){
                CacheUtil.remove(prefix);
            }

            //查询用户是否已经绑定过相同类型的第三方账号
            UserProfile userProfile = new UserProfile();
            userProfile.setProfiletype(queryUserProfile.getProfiletype());
            userProfile.setUserid(Long.valueOf(user.getUserId()));
            int count = userProfileService.queryUserProfileConflict(userProfile);
            if (count > 0) {
                json = this.setJson(false, "该账户已绑定过账号", null);
                return json;
            }
            String autoThirty = request.getParameter("autoThirty");// 是否30天自动登录
            // 执行登录操作
            userService.queryDoLogin(user,autoThirty,response,request);

            queryUserProfile.setUserid(Long.valueOf(user.getUserId()));
            //修改 userProfile 的 userId
            userProfileService.updateUserProfile(queryUserProfile);

            json = this.setJson(true, "success", "登录成功");
        } catch (Exception e) {
            logger.error("ThirdPartLoginController.bundling()---error", e);
            json = setAjaxException(json);
        }
        return json;
    }

    /**
     * 第三方绑定新注册的账号(相当于注册操作)
     * @param model
     * @param request
     * @param response
     * @param user
     * @param userProfileId 第三方账号userProfileId
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/front/ajax/bundingNew")
    @ResponseBody
    public Map<String, Object> bundingNew(HttpServletRequest request,
                                               HttpServletResponse response,
                                               ModelMap model,
                                                @ModelAttribute("user") User user,
                                               @RequestParam("userProfileId") Long userProfileId) {
        Map<String, Object> json = null;
        try {
            HttpSession session = request.getSession();
            String registerCode = request.getParameter("registerCode")==null?"":request.getParameter("registerCode");
            //从缓存提取手机验证码
            String mobileCodeNum = (String) CacheUtil.get(session.getId()+"_mobileCodeNum");
            //获得输入的手机验证码
            String mobileCode = request.getParameter("mobileCode")==null?"":request.getParameter("mobileCode");

            //注册开关配置
            Map<String,Object> registerController = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.registerController.toString()).get(WebSiteProfileType.registerController.toString());
            //检验手机验证码是否正确
            if ("ON".equals(registerController.get("phoneProving"))){
                if (!mobileCode.equals(mobileCodeNum)||mobileCodeNum==null){
                    json = this.setJson(false, "请输入正确的手机验证码！", null);
                    return json;
                }
            }
            if ("OFF".equals(registerController.get("phoneProving"))){
                Object randCode = request.getSession().getAttribute(CommonConstants.RAND_CODE);
                if(randCode==null || !registerCode.equals(randCode.toString())){
                    json = this.setJson(false, "请输入正确的验证码！", null);
                    return json;
                }
            }

            UserProfile queryUserProfile = userProfileService.queryUserProfileById(userProfileId);
            //验证第三方参数
            if (ObjectUtils.isNull(queryUserProfile)) {
                json = this.setJson(false, "参数错误！", null);
                return json;
            }

            user.setPicImg(userProfileService.download(user.getPicImg()));
            user.setRegisterFrom(UserRegisterFrom.OpenAppRegisterFrom.toString());
            json = userService.createDoRegister(request,response,user);

            if(Boolean.valueOf(json.get("success").toString())){
                queryUserProfile.setUserid(Long.valueOf(user.getUserId()));
                //修改 userProfile 的 userId
                userProfileService.updateUserProfile(queryUserProfile);
            }
        } catch (Exception e) {
            logger.error("userRegist error", e);
            json = setAjaxException(json);
        }
        return json;
    }
}
