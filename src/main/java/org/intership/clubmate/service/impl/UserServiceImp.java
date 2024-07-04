package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.UserMapper;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.Md5Util;
import org.intership.clubmate.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> getAll(IPage<User> page, Wrapper<User> queryWrapper) {

        return userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public User addUser(User user) {
        User res =userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId,user.getId()));
        if(res!=null){
            return null;
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
            return null;
        }else userMapper.updateById(user);
        return user;
    }

    @Override
    public User login(int id, String password) {
        User user =getById(id);
        if(user!=null){
            if(Md5Util.checkPassword(password,user.getPassword())){
                return user;
            }
            else {
                return null;
            }
        }
        else return null;
    }

    @Override
    public void register(User user) {
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId,user.getId()));
        if(user1!=null){
            throw new ServiceException("501","用户已存在");
        }else {
            String pattern = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$";
            if(user.getPassword().matches(pattern)){
               user.setPassword(Md5Util.getMD5String(user.getPassword())) ;
                userMapper.insert(user);
                System.out.println("注册成功");
            }else throw new ServiceException("510","密码不符合格式");

        }
    }

    @Override
    public User getById(int id) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getId,id));
        if(res==null){
            throw new ServiceException("505","用户不存在");
        }
        return res;
    }


}
