package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.entity.UserClubCollection;
import org.intership.clubmate.mapper.ClubMapper;
import org.intership.clubmate.mapper.UserClubCollectionMapper;
import org.intership.clubmate.mapper.UserMapper;
import org.intership.clubmate.service.UserClubCollectionService;

import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClubCollectionImp extends ServiceImpl<UserClubCollectionMapper, UserClubCollection> implements UserClubCollectionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClubMapper clubMapper;
    @Autowired
    private UserClubCollectionMapper userClubCollectionMapper;


    @Override
    public UserClubCollection add(UserClubCollection ucc) {
        UserClubCollection res=userClubCollectionMapper.selectOne(Wrappers.<UserClubCollection>lambdaQuery()
                .eq(UserClubCollection::getUserId,ucc.getUserId())
                .eq(UserClubCollection::getClubId,ucc.getClubId()));
        if(res!=null){
            return null;
        }
        userClubCollectionMapper.insert(ucc);
        return ucc;
    }

    @Override
    public void delete(int uid, int cid) {

        userClubCollectionMapper.delete(Wrappers.<UserClubCollection>lambdaQuery()
                .eq(UserClubCollection::getUserId,uid)
                .eq(UserClubCollection::getClubId,cid));

    }

    @Override
    public UserClubCollection findUserClubCollection(int uid, int cid) {
        UserClubCollection res=userClubCollectionMapper.selectOne(Wrappers.<UserClubCollection>lambdaQuery()
                .eq(UserClubCollection::getUserId,uid)
                .eq(UserClubCollection::getClubId,cid));
        if(res==null){
            return null;
        }
        return res;
    }


    @Override
    public IPage<UserClubCollection> getClub(IPage<UserClubCollection> page, Wrapper<UserClubCollection> queryWrapper) {

        return userClubCollectionMapper.selectPage(page,queryWrapper);
    }
}
