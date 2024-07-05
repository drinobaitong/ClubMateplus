package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.UCJoin;
import org.intership.clubmate.entity.User;

public interface UCJoinService extends IService<UCJoin> {
    void insert(Integer userId,Integer clubId);
    IPage<UCJoin> getUsers(Integer pageNo,Integer pageSize,Integer clubId);
    void quit(Integer userId,Integer clubId);
    void audit(Integer userId,Integer clubId,int status);

    IPage<UCJoin> getClubs(Integer pageNo,Integer pageSize,Integer userId);
}
