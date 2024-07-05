package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.intership.clubmate.entity.Club;


@Mapper
public interface ClubMapper extends BaseMapper<Club> {
    @Update("update club set total_number=total_number+1 where  id=#{id}")
    void addMember(Integer id);
    @Update("update club set total_number=total_number-1 where  id=#{id}")
    void subMember(Integer id);
}
