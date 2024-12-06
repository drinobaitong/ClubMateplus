package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.entity.Club;

import java.util.List;


@Mapper
public interface ClubMapper extends BaseMapper<Club> {
    @Update("update club set total_number=total_number+1 where  id=#{id}")
    void addMember(Integer id);
    @Update("update club set total_number=total_number-1 where  id=#{id}")
    void subMember(Integer id);

    @Select("select * from club where name LIKE CONCAT('%',#{search},'%')")
    List<Club> findClubFS(String search);


}
