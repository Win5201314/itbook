package com.inxedu.os.common.constants;


import com.inxedu.os.common.util.PropertyUtil;

/**
 * @ClassName com.inxedu.os.inxedu.common.entity.CommonConstant
 * @Create Date : 2015-3-17
 *  @author www.inxedu.com
 */
public class CommonConstants {

    public static String propertyFile = "project";
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(propertyFile);
    public static String contextPath = propertyUtil.getProperty("contextPath");
    public static String uploadServer = contextPath;
    public static String staticServer = contextPath;
    public static String groupPath = propertyUtil.getProperty("groupPath");
    public static String projectName = "inxedu";
    public static final String MYDOMAIN = propertyUtil.getProperty("mydomain");

    /**账户正则表达式 限10个字符，支持中英文、数字、减号或下划线 */
    public static String usernameRegex="^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,10}$";
    /**邮箱正则表达式*/
    public static String emailRegex="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    /**电话号码正则表达式*/
    public static String telRegex="^1[0-9]{10}$";
    /**用户密码正则表达式*/
    public static String usepwdRegex="^[_0-9a-zA-Z]{6,}$";
    /**后台用户登录名正则表达式*/
    public static String loginRegex = "^(?=.*[a-zA-Z])[a-zA-Z0-9]{6,20}$";
    /**图片验证码Session的K*/
    public static final String RAND_CODE="COMMON_RAND_CODE";

    // 资源文件版本号
    public static final String VERSION = System.currentTimeMillis() + "";
}
