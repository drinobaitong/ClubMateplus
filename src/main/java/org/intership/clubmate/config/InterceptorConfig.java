package org.intership.clubmate.config;

import org.intership.clubmate.config.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }


    // 加上拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 配置新的token登录拦截器和新的拦截规则，全部拦截
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }
}
