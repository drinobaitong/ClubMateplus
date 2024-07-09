package org.intership.clubmate.controller;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.utils.FileUtil;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/file")
public class FileController {

    //上传文件
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字是唯一的,从而防止文件覆盖
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = FileUtil.uploadFile(filename, file.getInputStream());
        return ResponseResult.success(url);
    }

    //依据文件名删除文件
    @DeleteMapping("/delete/{objectName}")
    public ResponseResult delete(@PathVariable("objectName")String objectName){
        FileUtil.deleteFile(objectName);
        if(FileUtil.findFile(objectName)==null){
            return ResponseResult.success();
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"删除失败！");
    }

    //得到文件名
    @GetMapping("/getname")
    public String getObjectName(String url){
        return FileUtil.getObjectName(url);
    }

}
