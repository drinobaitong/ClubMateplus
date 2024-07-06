package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.ErrorLog;
import org.intership.clubmate.entity.LoginLog;
import org.intership.clubmate.mapper.ErrorLogMapper;
import org.intership.clubmate.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogServiceImp extends ServiceImpl<ErrorLogMapper,ErrorLog> implements ErrorLogService {
    @Autowired ErrorLogMapper errorLogMapper;
    @Override
    public ErrorLog add(ErrorLog errorLog) {
        errorLogMapper.insert(errorLog);
        return null;
    }

    @Override
    public void delete(int id) {
        errorLogMapper.delete((Wrappers.<ErrorLog>lambdaQuery().eq(ErrorLog::getId,id)));

    }
}
