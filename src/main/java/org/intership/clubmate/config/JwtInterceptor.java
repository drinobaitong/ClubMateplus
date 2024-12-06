package org.intership.clubmate.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.UserMapper;
import org.intership.clubmate.utils.ServiceException;
import org.springframework.web.servlet.HandlerInterceptor;


public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //从http请求头获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("Authorization");
        }
        if (StrUtil.isBlank(token))
            throw new ServiceException("401", "权限不够，请登录");

        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException("401", "token错误");
        }

        User user = userMapper.selectById(Integer.valueOf(userId));
        // 如果查不到
        if (user == null)
            throw new ServiceException("401", "用户不存在");
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "token解析错误");
        }
        return true;
    }
}
