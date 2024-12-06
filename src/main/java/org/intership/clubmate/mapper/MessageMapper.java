package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.intership.clubmate.entity.Message;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
