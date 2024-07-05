package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.intership.clubmate.entity.UCJoin;

public interface UCJoinMapper extends BaseMapper<UCJoin> {
        @Select("select status from user_club_join where club_id=#{clubId} and user_id=#{userId}")
    int getStatus(Integer clubId,Integer userId);
}
