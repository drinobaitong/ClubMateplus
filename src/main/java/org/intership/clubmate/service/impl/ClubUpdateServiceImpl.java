package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Insert;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.ClubUpdate;
import org.intership.clubmate.mapper.ClubMapper;
import org.intership.clubmate.mapper.ClubUpdateMapper;
import org.intership.clubmate.service.ClubUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubUpdateServiceImpl extends ServiceImpl<ClubUpdateMapper, ClubUpdate> implements ClubUpdateService {

    @Autowired
    private ClubUpdateMapper clubUpdateMapper;
    @Autowired
    private ClubMapper clubMapper;
    @Override
    public void insertUpdate(ClubUpdate clubUpdate) {
        clubUpdateMapper.insert(clubUpdate);
    }

    @Override
    public void cancelUpdate(Integer id) {
        clubUpdateMapper.deleteById(id);
    }

    @Override
    public Club getClubById(Integer id) {
        Club club=new Club();
        ClubUpdate clubUpdate=clubUpdateMapper.selectById(id);
        club=clubMapper.selectById(id);
        //重置可能更新的属性
        club.setId(clubUpdate.getId());
        club.setName(clubUpdate.getName());
        club.setIntroduce(clubUpdate.getIntroduce());
        club.setCollage(clubUpdate.getCollage());
        club.setCreateUserId(clubUpdate.getCreateUserId());
        club.setTags(clubUpdate.getType());

        return club;
    }

    @Override
    public IPage<ClubUpdate> getUpdate() {

        Page<ClubUpdate> page=new Page<>();
        return clubUpdateMapper.selectPage(page,null);
    }
}
