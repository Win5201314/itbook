package com.inxedu.os.common.constants;

import com.inxedu.os.common.util.PropertyUtil;

/**
 * @description cache缓存相关常量
 *  @author www.inxedu.com
 */
public class CacheConstans {

	public static PropertyUtil webPropertyUtil = PropertyUtil.getInstance("memtimes");
	public static final String MEMFIX=webPropertyUtil.getProperty("memfix");
	public static final String WEBGROUPMEMFIX=webPropertyUtil.getProperty("web_group_memfix");
	public static final String MEM_ALL_SUBECJT= MEMFIX + "MEM_ALL_SUBECJT";
	public static final int MEM_ALL_SUBECJT_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_ALL_SUBECJT_TIME"));
	public static final int MEM_COMMON_TIME = 600;
	public static final String MEM_SUBECJT = MEMFIX + "MEM_SUBECJT_";
	public static final String RECOMMEND_COURSE = MEMFIX + "recommend_course";
	public static final int RECOMMEND_COURSE_TIME= Integer.parseInt(webPropertyUtil.getProperty("RECOMMEND_COURSE_TIME"));
	public static final String BANNER_IMAGES = MEMFIX + "banner_images";
	public static final int BANNER_IMAGES_TIME = Integer.parseInt(webPropertyUtil.getProperty("BANNER_IMAGES_TIME"));
	public static final String WEBSITE_PROFILE = MEMFIX + "website_profile";
	public static final int WEBSITE_PROFILE_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEBSITE_PROFILE_TIME"));
	public static final String WEBSITE_NAVIGATE = MEMFIX + "website_navigate";
	public static final int WEBSITE_NAVIGATE_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEBSITE_NAVIGATE_TIME"));
	public static final String INDEX_STUDENT_DYNAMIC = MEMFIX + "index_student_dynamic";
	public static final int INDEX_STUDENT_DYNAMIC_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_STUDENT_DYNAMIC_TIME"));

    /**前台登录用户ehcache前缀*/
    public static final String WEB_USER_LOGIN_PREFIX=WEBGROUPMEMFIX+"web_user_login";
    public static final int USER_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_TIME"));//前台登录用户缓存6小时
	public static final String USER_CURRENT_LOGINTIME = MEMFIX+"USER_CURRENT_LOGINTIME_";//记录当前用户当前的登录时间，下次登录时会更新此缓存

	/** 缓存后台登录用户ehcache前缀 */
	public static final String LOGIN_MEMCACHE_PREFIX =WEBGROUPMEMFIX+"login_sys_user";
	/** 后台所有用户权限缓存名前缀 **/
	public static final String SYS_ALL_USER_FUNCTION_PREFIX =WEBGROUPMEMFIX+"SYS_USER_ALL_FUNCTION_";
	/** 登录用户权限缓存名前缀 **/
	public static final String USER_FUNCTION_PREFIX =WEBGROUPMEMFIX+"SYS_USER_FUNCTION_";
	/** 前台首页 网校名师 缓存 **/
	public static final String 	INDEX_TEACHER_RECOMMEND = MEMFIX+"INDEX_TEACHER_RECOMMEND";
	/** 前台首页2 网校名师 缓存 **/
	public static final String 	INDEX2_TEACHER_RECOMMEND = MEMFIX+"INDEX2_TEACHER_RECOMMEND";
	/** 前台首页3 网校名师 缓存 **/
	public static final String 	INDEX3_TEACHER_RECOMMEND = MEMFIX+"INDEX3_TEACHER_RECOMMEND";
	/** 前台首页2 专业 课程 **/
	public static final String 	INDEX2_SUBJECT_COURSE = MEMFIX+"INDEX2_SUBJECT_COURSE";
	/** 前台首页3 专业 课程 **/
	public static final String 	INDEX3_SUBJECT_COURSE = MEMFIX+"INDEX3_SUBJECT_COURSE";
	/** 前台首页2 左侧 资讯  **/
	public static final String 	INDEX2_ARTICLE_LEFT = MEMFIX+"INDEX2_ARTICLE_LEFT";
	/** 前台首页3 左侧 资讯  **/
	public static final String 	INDEX3_ARTICLE_LEFT = MEMFIX+"INDEX3_ARTICLE_LEFT";
	/** 前台首页2 右侧 资讯  **/
	public static final String 	INDEX2_ARTICLE_RIGHT = MEMFIX+"INDEX2_ARTICLE_RIGHT";
	/** 前台首页3 右侧 资讯  **/
	public static final String 	INDEX3_ARTICLE_RIGHT = MEMFIX+"INDEX3_ARTICLE_RIGHT";
	/** 文章  好文推荐 缓存 **/
	public static final String 	ARTICLE_GOOD_RECOMMEND = MEMFIX+"ARTICLE_GOOD_RECOMMEND";

	/** 某个课程下的教师集合 **/
	public static final String 	COURSE_TEACHER_LIST = MEMFIX+"COURSE_TEACHER_LIST";

	/**网站统计*/
	public static final String WEB_STATISTICS = MEMFIX + "web_statistics";
	/** 网站最近30条活跃统计 */
	public static final String WEB_STATISTICS_THIRTY = MEMFIX + "web_statistics_thirty";
	/**缓存1小时*/
	public static final int WEB_STATISTICS_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEB_STATISTICS_TIME"));
	
	/**后台统计*/
	public static final String WEB_COUNT=MEMFIX+"WEB_COUNT";
	/**缓存1小时*/
	public static final int WEB_COUNT_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEB_STATISTICS_TIME"));


	public static final String HELP_CENTER = MEMFIX + "help_center";//帮助页面左侧菜单
	public static final int HELP_CENTER_TIME = Integer.parseInt(webPropertyUtil.getProperty("HELP_CENTER_TIME"));//缓存1小时;


	public static final String SHOPCART = MEMFIX + "shopcats";//购物车+userId
	public static final int SHOPCART_TIME = Integer.parseInt(webPropertyUtil.getProperty("SHOPCART_TIME"));//缓存1小时


	public static final String WX_ACCESS_TOKEN = MEMFIX + "WX_ACCESS_TOKEN";//微信access_token
	public static final int WX_ACCESS_TOKEN_TIME = Integer.parseInt(webPropertyUtil.getProperty("WX_ACCESS_TOKEN_TIME"));//缓存1小时;


	public static final String USER_CANLOOK = MEMFIX + "user_canlook_";//用户是否可观看某个课程+courseId
	public static final int USER_CANLOOK_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_CANLOOK_TIME"));//缓存6小时


	public static String ZHIFUBAO_IS_OPEN = "";//用户是否 购买支付宝
	public static String WEIXIN_IS_OPEN = "";//用户是否 购买微信
	public static String YIBAO_IS_OPEN = "";//用户是否 购买易宝
	public static String KUAIQIAN_IS_OPEN = "";//用户是否 购买快钱
	public static String COUPON_IS_OPEN = "";//用户是否 购买优惠券服务
	public static String QQ_LOGIN_IS_OPEN = "";//用户是否 购买QQ第三方登录服务
	public static String SINA_LOGIN_IS_OPEN = "";//用户是否 购买SINA第三方登录服务
	public static String ACCOUNT_IS_OPEN = "";//用户是否 购买 账户 服务
	public static String WEIXIN_LOGIN_IS_OPEN = "";//用户是否 购买WEIXIN第三方登录服务
	public static String MEMBER_IS_OPEN = "";//用户是否 购买 会员 服务

	public static String smsType = "";//短信类型
	public static String smssdkappid = "";//腾讯云短信 sdkappid
	public static String smsstrAppkey = "";//腾讯云短信 strAppkey


	public static final String EMAIL_CODE_USER_BUNDDING=MEMFIX + "EMAIL_CODE_USER_BUNDDING";//用户第三方邮箱注册验证码
	public static final int EMAIL_CODE_USER_BUNDDING_TIME=Integer.parseInt(webPropertyUtil.getProperty("EMAIL_CODE_USER_BUNDDING_TIME"));//缓存5分钟

	public static final String MOBILE_CODE_USER_BUNDDING=MEMFIX + "MOBILE_CODE_USER_BUNDDING";//用户第三方手机注册验证码
	public static final int MOBILE_CODE_USER_BUNDDING_TIME=Integer.parseInt(webPropertyUtil.getProperty("MOBILE_CODE_USER_BUNDDING_TIME"));//缓存5分钟

	public static final String LIVE_INDEX_RECOMMEND = MEMFIX + "LIVE_INDEX_RECOMMEND";//直播首页推荐

	/** 直播 展示互动 K值 缓存 **/
	public static final String 	INDEX_LIVE_GENSEEK = MEMFIX+"INDEX_LIVE_GENSEEK";

	public static final String INDEX_ALL_SUBJECT = MEMFIX + "INDEX_ALL_SUBJECT";//首页所有专业

	public static final int USER_COMMENT_TIME=Integer.parseInt(webPropertyUtil.getProperty("USER_COMMENT_TIME"));//缓存5分钟
}