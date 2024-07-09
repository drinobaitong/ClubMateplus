package org.intership.clubmate.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.UserClubCollection;

public interface UserClubCollectionService  extends IService<UserClubCollection> {
    public UserClubCollection add(UserClubCollection ucc);

    public void delete(int uid,int cid);

    public UserClubCollection findUserClubCollection(int uid,int cid);
    public IPage<Club> getClub(IPage<UserClubCollection> page,int uid);
}
