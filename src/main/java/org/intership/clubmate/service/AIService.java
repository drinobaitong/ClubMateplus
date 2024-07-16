package org.intership.clubmate.service;

import cn.hutool.core.net.multipart.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AIService {
    public String chatWithAI(String content);

    public String uploadKLB(MultipartFile files);
}
