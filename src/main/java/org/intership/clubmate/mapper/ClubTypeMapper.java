package org.intership.clubmate.mapper;

import org.apache.ibatis.annotations.*;
import org.intership.clubmate.entity.ClubType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ClubTypeMapper {

    @Insert("insert into club_type values (#{type})")
    int insertClubType(ClubType clubType);

    @Delete("delete from club_type where type=#{type};")
    int deleteClubType(ClubType clubType);

    @Update("update club_type set type=#{newtype} where type=#{type}")
    int updateClubType(@PathVariable("type")String type, @PathVariable("newtype")String newtype);

    @Select("select * from club_type where type=#{type};")
    ClubType selectClubType(String type);

    @Select("select * from club_type")
    List<ClubType> selectAll();

}
