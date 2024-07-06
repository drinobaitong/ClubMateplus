package org.intership.clubmate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.LoginLog;
import org.intership.clubmate.entity.User;

public interface LoginLogService extends IService<LoginLog> {
    public LoginLog add(LoginLog loginLog);
    public void delete(int id);
}
