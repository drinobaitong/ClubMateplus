package org.intership.clubmate.config;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ServerEndpoint("/chat")
@Component
public class WebSocketChatConfig {
//    private static  final Map<String,Session> onlineUsers=new ConcurrentHashMap<>();
//    /**
//     * 建立webSocket连接后，被调用
//     * @param session
//     */
//    @OnOpen
//    void onOpen(Session session){
//        //将session进行保存
//        onlineUsers.put("",session);
//        //广播消息
//    }
//
//
//    @OnMessage
//    public void onMessage(String message){
//
//    }
//
//    @OnClose
//    public void onClose(Session session){
//
//    }
}