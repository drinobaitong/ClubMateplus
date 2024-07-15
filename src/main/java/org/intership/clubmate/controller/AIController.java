package org.intership.clubmate.controller;

import cn.hutool.core.net.multipart.UploadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ai")
@Api(tags = "AI助手模块")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/chat")
    @ApiOperation(value = "和本地知识库对话")
    public ResponseResult chatWithAI(@RequestBody String content) {
        return ResponseResult.success(aiService.chatWithAI(content));
    }

    @PostMapping("/upload")
    public ResponseResult uploadToKLB(@RequestPart("files") MultipartFile file){
        return ResponseResult.success(aiService.uploadKLB(file));
    }


}

