package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.ClubUpdate;
import org.intership.clubmate.entity.UCJoin;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.mapper.UCJoinMapper;
import org.intership.clubmate.mapper.UserMapper;
import org.intership.clubmate.service.UCJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UCJoinServiceImpl extends ServiceImpl<UCJoinMapper, UCJoin> implements UCJoinService {
    @Autowired
    private UCJoinMapper ucJoinMapper;


    @Override
    public void insert(Integer userId,Integer clubId,int rank) {
        UCJoin  ucJoin=new UCJoin(userId,clubId,0, LocalDateTime.now(),rank);
        ucJoinMapper.insert(ucJoin);
    }

    @Override
    public void delete(Integer userId, Integer clubId) {
        QueryWrapper<UCJoin>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("club_id",clubId);
        ucJoinMapper.delete(queryWrapper);
    }

    @Override
    public IPage<UCJoin> getUsers(Integer pageNo,Integer pageSize,Integer clubId) {
        Page<UCJoin> page=new Page<>(pageNo,pageSize);
        //先获取所有的id
        QueryWrapper<UCJoin> UCWrapper=new QueryWrapper<>();
        UCWrapper.eq("club_id",clubId).eq("status",2);


        return ucJoinMapper.selectPage(page,UCWrapper);
    }

    @Override
    public void quit(Integer userId, Integer clubId) {
        UpdateWrapper<UCJoin> queryWrapper=new UpdateWrapper<>();
        queryWrapper.set("status",1).eq("user_id",userId).eq("club_id",clubId);
        ucJoinMapper.update(queryWrapper);
    }

    @Override
    public void audit(Integer userId, Integer clubId, int status) {
        UpdateWrapper<UCJoin> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",status).eq("user_id",userId).eq("club_id",clubId);
        ucJoinMapper.update(updateWrapper);
    }

    @Override
    public IPage<UCJoin> getClubs(Integer pageNo, Integer pageSize, Integer userId) {
        Page<UCJoin> page=new Page<>(pageNo,pageSize);
        //先获取所有的id
        QueryWrapper<UCJoin> UCWrapper=new QueryWrapper<>();
        UCWrapper.eq("user_id",userId);


        return ucJoinMapper.selectPage(page,UCWrapper);
    }

    @Override
    public IPage<UCJoin> getJoins(Integer pageNo, Integer pageSize, Integer clubId) {
        Page<UCJoin> page=new Page<>(pageNo,pageSize);
        //先获取所有的id
        QueryWrapper<UCJoin> UCWrapper=new QueryWrapper<>();
        UCWrapper.eq("club_id",clubId).eq("status",0);


        return ucJoinMapper.selectPage(page,UCWrapper);
    }

    @Override
    public IPage<UCJoin> getQuits(Integer pageNo, Integer pageSize, Integer clubId) {
        Page<UCJoin> page=new Page<>(pageNo,pageSize);
        //先获取所有的id
        QueryWrapper<UCJoin> UCWrapper=new QueryWrapper<>();
        UCWrapper.eq("club_id",clubId).eq("status",1);


        return ucJoinMapper.selectPage(page,UCWrapper);
    }

    @Override
    public IPage<UCJoin> getControlClubs(Integer pageNo, Integer pageSize, Integer userId) {
        Page<UCJoin> page=new Page<>();
        QueryWrapper<UCJoin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("rank",1);
        return ucJoinMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int getStatus(Integer clubId, Integer userId) {
        return ucJoinMapper.getStatus(clubId,userId);
    }

    @Override
    public void setRank(Integer clubId, Integer userId, int rank) {
        UpdateWrapper<UCJoin> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("rank",rank).eq("club_id",clubId).eq("user_id",userId);
        ucJoinMapper.update(updateWrapper);
    }

    @Override
    public UCJoin ifExit(Integer userId, Integer clubId) {
        QueryWrapper<UCJoin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("club_id",clubId);
        return ucJoinMapper.selectOne(queryWrapper);
    }

}
