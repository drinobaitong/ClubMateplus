package org.intership.clubmate.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.UserMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

public class TokenUtils  {
    private static UserMapper staticUserMapper;

    @Resource
    private UserMapper userMapper;

    @PostConstruct
    public void setUserService(){
        staticUserMapper = userMapper;
    }


    public static String genToken(String userId,String sign){
        // 设置 claims 也就是 payload，可以括号里面添加多个参数
        return JWT.create().withAudience(userId)
                // 设置 token 过期时间 24 小时
                .withExpiresAt(DateUtil.offsetHour(new Date(),24))
                // 设置 sign 作为秘钥
                .sign(Algorithm.HMAC256(sign));
    }

    // 获取当前登录的用户信息
    public static User getCurrUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotEmpty(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(userId);
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
}
