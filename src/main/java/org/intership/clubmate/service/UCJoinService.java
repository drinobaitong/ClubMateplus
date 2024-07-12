package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.UCJoin;
import org.intership.clubmate.entity.User;

import javax.persistence.criteria.CriteriaBuilder;

public interface UCJoinService extends IService<UCJoin> {
    void insert(Integer userId,Integer clubId);
    void delete(Integer userId,Integer clubId);
    IPage<UCJoin> getUsers(Integer pageNo,Integer pageSize,Integer clubId);
    void quit(Integer userId,Integer clubId);
    void audit(Integer userId,Integer clubId,int status);

    IPage<UCJoin> getClubs(Integer pageNo,Integer pageSize,Integer userId);

    IPage<UCJoin> getJoins(Integer pageNo,Integer pageSize,Integer clubId);
    IPage<UCJoin> getQuits(Integer pageNo,Integer pageSize,Integer clubId);

    int getStatus(Integer clubId,Integer userId);
    void setRank(Integer clubId,Integer userId,int rank);
}
