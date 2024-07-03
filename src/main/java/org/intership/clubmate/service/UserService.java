package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.User;

public interface UserService extends IService<User> {
    public Page<User> getAll(Page<User> page,Wrapper<User> queryWrapper);

    public User addUser(User user);

    public void deleteUser(int id);

    public User updateUser(User user);

    public User login(int id,String password);

    public void register(User user);

    public User getById(int id);

}
