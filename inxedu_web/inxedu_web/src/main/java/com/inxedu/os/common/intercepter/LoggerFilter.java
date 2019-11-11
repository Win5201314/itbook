package com.inxedu.os.common.intercepter;

import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by yzl16 on 2016/7/11.
 */
public class LoggerFilter extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
    private String[] excludeUrls;

    public LoggerFilter() {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = WebUtils.getIpAddr(request);
        String path = request.getContextPath() + request.getServletPath();
        if(!ObjectUtils.isNull(this.excludeUrls)) {
            String[] buffer = this.excludeUrls;
            int enume = buffer.length;
            for(int key = 0; key < enume; ++key) {
                String value = buffer[key];
                if(path.contains(value)) {
                    return true;
                }
            }
        }
        StringBuffer var10 = new StringBuffer("");
        String var12;
        String[] var13;
        for(Enumeration var11 = request.getParameterNames(); var11.hasMoreElements(); var10.append(var12).append("=").append(Arrays.toString(var13))) {
            var12 = (String)var11.nextElement();
            var13 = request.getParameterValues(var12);
            if(var10.toString().length() > 0) {
                var10.append("  ");
            }
        }
        logger.info("+++user_access_log,ip=" + ip + ",uid:" + SingletonLoginUtils.getLoginUserId(request) + ",url=" + path + ",parameter=" + var10);
        return true;
    }
    public String[] getExcludeUrls() {
        return this.excludeUrls;
    }
    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
