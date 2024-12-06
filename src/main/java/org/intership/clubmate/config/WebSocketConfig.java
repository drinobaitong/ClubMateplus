package org.intership.clubmate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    //注入ServerEndpointExporter,扫描@ServerEndpoint注解的类
    public ServerEndpointExporter serverEndpointConfigurator(){
        return new ServerEndpointExporter();
    }
}
