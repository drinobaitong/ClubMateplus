package org.intership.clubmate.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.UserMapper;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<User> getAll(Page<User> page,Wrapper<User> queryWrapper) {

        return userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public User addUser(User user) {
        User res =userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId,user.getId()));
        if(res!=null){
            throw new ServiceException("501","用户已存在");
        }else{
            userMapper.insert(user);
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        User res =userMapper.selectOne(queryWrapper);
        if(res==null){
            throw new ServiceException("505","用户不存在");
        }else userMapper.delete(queryWrapper);
    }

    @Override
    public User updateUser(User user) {
        User res =userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId,user.getId()));
        if(res==null){
            throw new ServiceException("505","用户不存在");
        }else userMapper.updateById(user);
        return user;
    }
}
