package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.LoginLog;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.LoginLogMapper;
import org.intership.clubmate.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImp extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;


    @Override
    public LoginLog add(LoginLog loginLog) {
        loginLogMapper.insert(loginLog);
        return loginLog;
    }

    @Override
    public void delete(int id) {
        loginLogMapper.delete((Wrappers.<LoginLog>lambdaQuery().eq(LoginLog::getId,id)));
    }
}
