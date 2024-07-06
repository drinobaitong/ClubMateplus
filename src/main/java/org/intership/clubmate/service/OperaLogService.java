package org.intership.clubmate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.OperaLog;

public interface OperaLogService extends IService<OperaLog> {
    public OperaLog add(OperaLog operaLog);
    public void delete(int id);
}
