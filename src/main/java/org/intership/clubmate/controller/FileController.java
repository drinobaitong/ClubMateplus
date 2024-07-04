package org.intership.clubmate.controller;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.utils.FileUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字是唯一的,从而防止文件覆盖
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = FileUtil.uploadFile(filename, file.getInputStream());
        return ResponseResult.success(url);
    }
}
