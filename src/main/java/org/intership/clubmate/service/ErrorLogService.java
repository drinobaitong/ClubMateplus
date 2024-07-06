package org.intership.clubmate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.ErrorLog;

public interface ErrorLogService extends IService<ErrorLog> {
    public ErrorLog add(ErrorLog errorLog);
    public void delete(int id);
}
