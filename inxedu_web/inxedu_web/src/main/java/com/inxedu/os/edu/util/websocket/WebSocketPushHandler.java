package com.inxedu.os.edu.util.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebSocketPushHandler implements WebSocketHandler {
    private static final List<WebSocketSession> users = new ArrayList<WebSocketSession>();

    // 用户连接 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功进入了系统。。。");
        String userNoCurrent = String.valueOf(session.getAttributes().get("userNo"));
        //是否将当前这个session放入集合
        boolean isPutInSession = true;  
        for(WebSocketSession wsSession : users){
        	String userNo = (String)wsSession.getAttributes().get("userNo");
    		if (userNoCurrent.equals(userNo)) {
    			isPutInSession = false;
    		}
        }
        if(isPutInSession){
        	users.add(session);
        }
        sendMessagesToUsers(new TextMessage("客户端已与服务器建立了连接...."));
    }

    //用户发送业务数据
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 将消息进行转化，因为是消息是json数据，可能里面包含了发送给某个人的信息，所以需要用json相关的工具类处理之后再封装成TextMessage，
        // 我这儿并没有做处理，消息的封装格式一般有{from:xxxx,to:xxxxx,msg:xxxxx}，来自哪里，发送给谁，什么消息等等
	   	 if(message.getPayloadLength()==0){
	      	return;
	      }
	 	 String msgString = message.getPayload().toString();
//	 	 TextMessage msg = new TextMessage(msgString) ;
         // 给所有用户群发消息
//         sendMessagesToUsers(msg);
        // 给指定用户群发消息
//        sendMessageToUser(userId, msg);
        	 
	 	Message msg=new Gson().fromJson(msgString,Message.class);
		msg.setDate(new Date());
//		发给所有人		
//		sendMessagesToUsers( new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));	 
//		发给指定的人		
		sendMessageToUser(msg.getTo(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));	 

    }

    // 后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    // 用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);
        System.out.println("安全退出了系统");

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                // isOpen()在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息给指定的用户
     */
    public void sendMessageToUser(String userNo, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("userNo").equals(userNo)) {
                try {
                    // isOpen()在线就发送
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}