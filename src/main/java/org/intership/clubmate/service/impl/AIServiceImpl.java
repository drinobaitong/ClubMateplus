package org.intership.clubmate.service.impl;

import cn.hutool.core.net.multipart.UploadFile;
import org.intership.clubmate.service.AIService;
import org.intership.clubmate.utils.LangChainSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIServiceImpl implements AIService {
    @Autowired
    private LangChainSDK langChainSDK;

    @Override
    public String chatWithAI(String content) {
        return langChainSDK.chatWithAI(content).block();
    }

    @Override
    public String uploadKLB(MultipartFile files) {
        return langChainSDK.uploadToKLB(files).block();
    }
}
