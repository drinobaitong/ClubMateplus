package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.OperaLog;
import org.intership.clubmate.mapper.OperaLogMapper;
import org.intership.clubmate.service.OperaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperaLogServiceImp extends ServiceImpl<OperaLogMapper, OperaLog> implements OperaLogService {

    @Autowired
    private OperaLogMapper operaLogMapper;


    @Override
    public OperaLog add(OperaLog operaLog) {
        operaLogMapper.insert(operaLog);
        return operaLog;
    }

    @Override
    public void delete(int id) {
        operaLogMapper.delete((Wrappers.<OperaLog>lambdaQuery().eq(OperaLog::getId,id)));
    }
}
