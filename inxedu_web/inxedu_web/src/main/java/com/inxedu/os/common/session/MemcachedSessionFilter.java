package com.inxedu.os.common.session;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemcachedSessionFilter extends HttpServlet implements Filter {

	/**
	 *
	 */
	private static final long serialVersionUID = -365105405910803550L;

	// private FilterConfig filterConfig;

	private String sessionId = "sessionid";

	private String cookieDomain = CommonConstants.MYDOMAIN;

	private String cookiePath = "/";

	public void doFilter(ServletRequest servletRequest,
						 ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//如果cookie中有sessionId,则用sessionId
		String sid = WebUtils.getCookie(request,sessionId);
		//如果cookie中没有sessionId,则重新生成一个seesionId和cookie
		if (sid == null || sid.length() == 0) {
			sid = java.util.UUID.randomUUID().toString();
			Cookie cookie = new Cookie(sessionId, sid);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			if (org.apache.commons.lang.StringUtils.isNotEmpty(cookieDomain)) {
				cookie.setDomain(cookieDomain);
			}
			response.addCookie(cookie);
		}
		filterChain.doFilter(new HttpServletRequestWrapper(sid, request),
				servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
