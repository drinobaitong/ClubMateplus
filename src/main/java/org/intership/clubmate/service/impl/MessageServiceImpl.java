package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.Department;
import org.intership.clubmate.entity.Message;
import org.intership.clubmate.mapper.DepartmentMapper;
import org.intership.clubmate.mapper.MessageMapper;
import org.intership.clubmate.service.DepartmentService;
import org.intership.clubmate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService{
    @Autowired
    private  MessageMapper messageMapper;

    @Override
    public void insert(Integer userId, String content) {
        Message message=new Message();
        message.setUserId(userId);
        message.setStatus(0);
        message.setContent(content);
        message.setCreateTime(LocalDateTime.now());
        messageMapper.insert(message);
    }

    @Override
    public void read(Integer id) {
        UpdateWrapper<Message> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("status",1).eq("id",id);
        messageMapper.update(updateWrapper);
    }

    @Override
    public IPage<Message> getAllMessage(int pageNo,int pageSize,Integer userId) {
        Page<Message> pages=new Page<>(pageNo,pageSize);
        QueryWrapper<Message> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        return messageMapper.selectPage(pages,queryWrapper);
    }
}
