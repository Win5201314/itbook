package com.inxedu.os.edu.util.websocket;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 此类用来获取登录用户信息并交由websocket管理
 */
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler arg2,
            Map<String, Object> arg3) throws Exception {
        // 将ServerHttpRequest转换成request请求相关的类，用来获取request域中的用户信息
    	String userNo = "";
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            userNo = httpRequest.getParameter("userNo");
            if(StringUtils.isNotEmpty(userNo)){
            	arg3.put("userNo", userNo);
            	System.out.println("连接到我了");
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
        // TODO Auto-generated method stub

    }

}