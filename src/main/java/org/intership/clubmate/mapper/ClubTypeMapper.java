package org.intership.clubmate.mapper;

import org.apache.ibatis.annotations.*;
import org.intership.clubmate.entity.ClubType;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface ClubTypeMapper {

    @Insert("insert into clubtype values (#{type})")
    int insertClubType(ClubType clubType);

    @Delete("delete from clubtype where type=#{type};")
    int deleteClubType(ClubType clubType);

    @Update("update clubtype set type=#{newtype} where type=#{type}")
    int updateClubType(@PathVariable("type")String type, @PathVariable("newtype")String newtype);

    @Select("select * from clubtype where type=#{type};")
    ClubType selectClubType(String type);

}
