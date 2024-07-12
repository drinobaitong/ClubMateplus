package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.UserClubCollection;

@Mapper
public interface UserClubCollectionMapper extends BaseMapper<UserClubCollection> {
    @Select("select c.* from user_club_collection u left join club c on c.id=u.club_id " +
            "where u.user_id = #{uid}")
    IPage<Club> selectClub(IPage<UserClubCollection> page,int uid);

}
