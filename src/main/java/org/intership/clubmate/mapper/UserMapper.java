package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.intership.clubmate.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into user_club_join values (#{userId},#{clubId},#{status});")
    void insertIntoClub(Integer userId,Integer clubId,int status);
}
