package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Message;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message/{userId}")
    public ResponseResult getAllMes(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize

    ){
        log.info("获取用户所有信息");
        IPage<Message> messages=messageService.getAllMessage(pageNo,pageSize,userId);
        return ResponseResult.success(messages);
    }

    @PostMapping("/message/read")
    public ResponseResult readMes(
            @RequestParam Integer id
    ){
        log.info("用户查看消息:"+id);
        messageService.read(id);
        return ResponseResult.success();
    }
}
