package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.mapper.ClubMapper;
import org.intership.clubmate.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {
    @Autowired
    private ClubMapper clubMapper;
    @Override
    public void insertClub(Club club){
        club.setRegisterTime(LocalDateTime.now());
        club.setStatus(0);
        club.setTotalNumber(1);
        clubMapper.insert(club);
    }

    @Override
    public void deleteById(Integer id) {
        clubMapper.deleteById(id);
    }

    @Override
    public Club getById(Integer id) {
        return  clubMapper.selectById(id);
    }

    @Override
    public IPage<Club> list(int pageNo, int pageSize) {
        Page<Club> page=new Page<>(pageNo,pageSize);
        QueryWrapper<Club> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderBy(true,true,"status");
        return clubMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void update(Club club) {
        // 构建更新条件
        UpdateWrapper<Club> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", club.getId());  // 假设更新条件基于主键id

        // 执行更新操作
        clubMapper.update(club,updateWrapper);
    }

    @Override
    public IPage<Club> typeList(int pageNo, int pageSize, int ty) {
        Page<Club> pages=new Page<>(pageNo,pageSize);
        QueryWrapper<Club> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tags",ty);
        return clubMapper.selectPage(pages,queryWrapper);
    }

    @Override
    public IPage<Club> collageList(int pageNo, int pageSize, String collage) {
        Page<Club> pages=new Page<>(pageNo,pageSize);
        QueryWrapper<Club> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("collage",collage);
        return clubMapper.selectPage(pages,queryWrapper);
    }

    @Override
    public void audit(int status,Integer id) {
        UpdateWrapper<Club> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",status).eq("id",id);

        clubMapper.update(updateWrapper);
    }

    @Override
    public void updateImage(Integer clubId, String url) {
        UpdateWrapper<Club> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("avatar_url",url).eq("id",clubId);

        clubMapper.update(updateWrapper);
    }

    @Override
    public void addMember(Integer clubId) {
        clubMapper.addMember(clubId);
    }

    @Override
    public void subMember(Integer clubId) {
        clubMapper.subMember(clubId);
    }

}
