package com.inxedu.os.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.entity.PageEntity;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtils {
	public static String MYDOMAIN = CommonConstants.MYDOMAIN;
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	public static void setCookie(HttpServletResponse response, String key, String value, int days) {
		setCookie(response, key, value, days, MYDOMAIN);
	}

	/**设置Cookie值*/
	public static void setCookie(HttpServletResponse response, String key, String value, int days, String domain) {
		if ((key != null) && (value != null)) {
			Cookie cookie = new Cookie(key, value);
			cookie.setMaxAge(days * 24 * 60 * 60);
			cookie.setPath("/");
			if (org.apache.commons.lang.StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		}
	}

	/**获取Cookie值*/
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		String resValue = "";
		if ((cookies != null) && (cookies.length > 0)) {
			for (int i = 0; i < cookies.length; i++) {
				if ((!key.equalsIgnoreCase(cookies[i].getName()))
						|| (!org.apache.commons.lang.StringUtils
								.isNotEmpty(cookies[i].getValue())))
					continue;
				resValue = cookies[i].getValue();
			}

		}
		return resValue;
	}

	/**删除默认域名下的*/
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		deleteCookieDomain(request, response, name, MYDOMAIN);
	}

	/**删除某域下的Cookie*/
	public static void deleteCookieDomain(HttpServletRequest request,
			HttpServletResponse response, String name, String domain) {
		Cookie[] cookies = request.getCookies();
		if ((cookies != null) && (cookies.length > 0))
			for (int i = 0; i < cookies.length; i++) {
				if (!name.equalsIgnoreCase(cookies[i].getName()))
					continue;
				Cookie ck = new Cookie(cookies[i].getName(), null);
				ck.setPath("/");
				if (org.apache.commons.lang.StringUtils.isNotEmpty(domain)) {
					ck.setDomain(domain);
				}
				ck.setMaxAge(0);
				response.addCookie(ck);
				return;
			}
	}
	/**获取k位由0-9的随机数字拼成的符串*/
	public static String getRandomNum(int k){
		String RandomNum = "";
		for (int i=0;i<k;i++){
			Random rd = new Random();
			RandomNum += (int)Math.floor(rd.nextInt(9));
		}
		return RandomNum;
	}
	/**获取请求IP地址*/
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ipAddress == null) || (ipAddress.length() == 0)
				|| ("unknown".equalsIgnoreCase(ipAddress))) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		if ((ipAddress != null) && (ipAddress.length() > 15)) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}


	/**获取请求URL*/
	public static String getServletRequestUrlParms(HttpServletRequest request) {
		StringBuffer sbUrlParms = request.getRequestURL();
		sbUrlParms.append("?");
		@SuppressWarnings("unchecked")
		Enumeration<String> parNames = request.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
						.append(URLEncoder.encode(request.getParameter(parName), "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		return sbUrlParms.toString();
	}
	/**获取请求URI*/
	public static String getServletRequestUriParms(HttpServletRequest request) {
		StringBuffer sbUrlParms = new StringBuffer(request.getRequestURI());
		sbUrlParms.append("?");
		@SuppressWarnings("unchecked")
		Enumeration<String> parNames = request.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
				.append(URLEncoder.encode(request.getParameter(parName), "UTF-8"))
				.append("&");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		return sbUrlParms.toString();
	}
	
	/**后台用户登录号验证*/
	public static boolean checkLoginName(String value){
		return value.matches(CommonConstants.loginRegex);
	}

	/**正则表达试验证账户号*/
	public static boolean checkUsername (String value) {
		return (value.matches(CommonConstants.usernameRegex));
	}

	/**正则表达试验证邮箱号*/
	public static boolean checkMobile (String value) {
		return (value.matches(CommonConstants.telRegex));
	}
	/**正则表达试验证邮箱号*/
	public static boolean checkEmail(String value, int length) {
		if (length == 0) {
			length = 40;
		}
		return (value.matches(CommonConstants.emailRegex)) && (value.length() <= length);
	}

	/**正则表达试验证密码*/
	public static boolean isPasswordAvailable(String password) {
		boolean flag = (password.matches(CommonConstants.usepwdRegex)) && (password.length() >= 6) && (password.length() <= 16);
		return flag;
	}

	/**判断如果是ajax请求*/
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String her = request.getHeader("x-requested-with");

		return org.apache.commons.lang.StringUtils.isNotEmpty(her);
	}
	/**判断如果不是ajax请求*/
	public static boolean isNotAjaxRequest(HttpServletRequest request) {
		return !isAjaxRequest(request);
	}
	
	/**获取用户访问浏览器信息*/
	public static String getUserAgent(HttpServletRequest request) {
    	String uabrow = request.getHeader("User-Agent");//获取浏览器信息
    	UserAgent userAgent =UserAgent.parseUserAgentString(uabrow);
    	Browser browser = userAgent.getBrowser();
        OperatingSystem os = userAgent.getOperatingSystem();
    	return browser.getName().toLowerCase()+";"+os.getName().toLowerCase();
    }
	/* 判断referer*/
	public static boolean compareReferer(String referer,String trueUrl){
		String[]baseUrl = referer.split("/");
		String refererUrl = baseUrl[0]+"//"+baseUrl[2];
		return refererUrl.equals(trueUrl);
	}
	/*访问次数限制register：要判断是否超过限制的邮箱或手机 ip ：用户ip  registerLimit：对应的邮箱或手机验证的限制次数 ipLimit：ip注册的限制次数*/
	public static boolean registerLimit(String register,String ip,int registerLimit,int ipLimit){
		//如果缓存中没有该用户ip就将该ip和请求次数1存入缓存1天
		if (CacheUtil.get(ip)==null){
			//把ip和验证码请求次数放入缓存
			CacheUtil.set(ip,0,86400);
		}
		//获取缓存中对应ip的请求次数
		int ipCount = (int)CacheUtil.get(ip);
		if (CacheUtil.get(register)==null){
			//把请求用户信息和验证码请求次数放入缓存
			CacheUtil.set(register,0,86400);
		}
		//发送验证码的次数
		int registerCoun=(int)CacheUtil.get(register);
		if (ipCount>=ipLimit){
			return false;
		}
		if (registerCoun>registerLimit){
			return false;
		}
		//如果缓存中有该用户ip,邮箱，且请求次数小于5，就将该ip和请求次数加1存入缓存1天
		if (ipCount<=ipLimit&&registerCoun<=registerLimit){
			ipCount ++;
			registerCoun ++;
			//把ip和验证码请求次数放入缓存
			CacheUtil.set(ip,ipCount,86400);
			//把邮箱和验证码请求次数放入缓存
			CacheUtil.set(register,registerCoun,86400);
			return true;
		}
		return true;
	}

    /**去html*/
    public static String replaceTagHTML(String src) {
        String regex = "\\<(.+?)\\>";
        return org.apache.commons.lang.StringUtils.isNotEmpty(src)?src.replaceAll(regex, ""):"";
    }
	/**去script*/
	public static String replaceTagSCRIPT(String src) {
		return getRegExString(src, regEx_script);
	}
	/**去style*/
	public static String replaceTagSTYLE(String src) {
		return getRegExString(src, regEx_style);
	}
	//根据正则替换字符串
	private static String getRegExString(String src, String regEx) {
		if(src==null) return "";
		Pattern p_script = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(src);
		src = m_script.replaceAll("");
		return src.trim();
	}

	/**
	 * 设置ajax请返回结果
	 *
	 * @param success
	 *            请求状态
	 * @param message
	 *            提示信息
	 * @param entity
	 *            返回数据结果对象
	 */
	public static Map<String,Object> setJson(boolean success, String message, Object entity) {
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success", Boolean.valueOf(success));
		json.put("message", message);
		json.put("entity", entity);
		return json;
	}

	/**
	 * 根据list 和 page 进行分页
	 * @param list 数据
	 * @param pageEntity 分页参数
     * @return
     */
	public static List pageList(List list, PageEntity pageEntity){
		List newList = new ArrayList();
		//分页的大小
		int size = pageEntity.getPageSize();
		//当前页
		int page = pageEntity.getCurrentPage();
		for (int i=0;i<size;i++){
			//根据分页 和 当前页获取 分页list的下标 ，根据下标获取
			int index = size*(page-1)+i;
			if(index<pageEntity.getTotalResultSize()){
				newList.add(list.get(index));
			}
		}
		return newList;
	}

}