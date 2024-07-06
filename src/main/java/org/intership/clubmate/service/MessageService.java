package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Message;

public interface MessageService extends IService<Message> {
    void insert(Integer useId,String content);

    void read(Integer id);

}
