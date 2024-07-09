package org.intership.clubmate.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
//    @Override
//    public void modifyHandShake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response){
//        HttpSession httpSession=(HttpSession) request.getHttpSession();
//        //将httpSession对象存到配置对象中
//        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
//    }
}
