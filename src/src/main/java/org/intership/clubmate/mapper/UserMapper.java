package org.intership.clubmate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.intership.clubmate.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findById(int id);

    User updateByUid(User user);
}
