package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.ClubUpdate;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ArticleService;
import org.intership.clubmate.service.ClubService;
import org.intership.clubmate.service.ClubUpdateService;
import org.intership.clubmate.service.MessageService;
import org.intership.clubmate.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@CrossOrigin(origins="*")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ClubUpdateService clubUpdateService;
    @Autowired
    private MessageService messageService;
    //新建社团
    @PostMapping("/club/insert")
    public ResponseResult insertClub(@RequestBody Club club){
        log.info("新建社团");
        clubService.insertClub(club);
       // messageService.insert(club.getCreateUserId(),"您的社团："+club.getName()+"的创建申请已提交，目前正等待审核");
        return ResponseResult.success();
    }

    //删除社团(同时删除社团内所有文章)
    @DeleteMapping("/club/delete/{id}")
    @Transactional
    public ResponseResult deleteClub(@PathVariable Integer id){
        Club club=clubService.getById(id);
        log.info("删除社团");
        if(club!=null) {
            //删除社团内所有文章
            List<Article> articles=articleService.getArticlesByClub(club.getId());
           for(Article article:articles){
               articleService.deleteArticle(article);
           }
            //删除社团
            clubService.deleteById(id);
            return ResponseResult.success();
        }else{
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"社团不存在");
        }
    }
    @GetMapping("/club/get/{id}")
    public ResponseResult getClub(@PathVariable Integer id){
        Club club=clubService.getById(id);
        log.info("根据id查找社团");
        return  ResponseResult.success(club);
    }

    @GetMapping("/club/list")
    public ResponseResult listClub(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        log.info("分页查询");
        IPage<Club> clubs=clubService.list(pageNo,pageSize);
        return ResponseResult.success(clubs);
    }

    @GetMapping("/club/list/tags")
    public ResponseResult listByType(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam (value = "tags")int tags){
        log.info("社团分类列表");
        IPage<Club> clubs=clubService.typeList(pageNo,pageSize,tags);
        return ResponseResult.success(clubs);
    }

    @GetMapping("/club/list/collage")
    public ResponseResult listByCollage(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam (value = "collage")String collage
    ){
        log.info("社团学院列表");
        IPage<Club> clubs=clubService.collageList(pageNo,pageSize,collage);
        return ResponseResult.success(clubs);
    }
    @PutMapping("/club/update")
    public ResponseResult updateClub(@RequestBody ClubUpdate clubUpdate){

        log.info("社团更新请求");
        clubUpdateService.insertUpdate(clubUpdate);
        return ResponseResult.success();
    }

    @PutMapping("/club/audit/{id}")
    public ResponseResult audit(
            @RequestParam int status,
            @PathVariable Integer id
    ){
        Club club=clubService.getById(id);
        if(status== 1){
            log.info("社团创建审核通过");
            messageService.insert(club.getCreateUserId(),"您的社团："+club.getName()+"创建申请已通过");
        }else {
            log.info("社团创建审核未通过");
            messageService.insert(club.getCreateUserId(),"您的社团："+club.getName()+"创建申请未能通过");
        }
        clubService.audit(status,id);
        return ResponseResult.success();
    }

    @PostMapping("/club/image/{id}")
    public ResponseResult uploadImage(
            @PathVariable Integer id,
            @RequestParam MultipartFile file
    ) throws Exception {
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字是唯一的,从而防止文件覆盖
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = FileUtil.uploadFile(filename, file.getInputStream());
        clubService.updateImage(id,url);
        return ResponseResult.success();
    }

    @GetMapping("/club/fuzzysearch")
    public ResponseResult fuzzysearchClub(String search){
        List<Club> clubs=clubService.getClubFS(search);
        if(clubs.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未能查找到社团");
        }else return ResponseResult.success(clubs);
    }

    @PostMapping("/club/update/audit")
    public ResponseResult auditUpdate(
            @RequestParam Integer id,
            @RequestParam int status
    ){

        if(status==1){
            log.info("社团更新审核通过");
            Club club=clubUpdateService.getClubById(id);
            clubService.update(club);
            //发送通知消息
            messageService.insert(club.getCreateUserId(),"您的社团"+club.getName()+"更新请求已通过");
            clubUpdateService.cancelUpdate(id);

        }else{
            log.info("社团更新审核未通过");
            //删除表的更新请求
            clubUpdateService.cancelUpdate(id);
            Club club=clubUpdateService.getClubById(id);
            //发送通知信息
            messageService.insert(club.getCreateUserId(),"您的社团"+club.getName()+"更新请求未能通过");
        }
        return ResponseResult.success();
    }

    @GetMapping("club/update/list")
    public ResponseResult updateList(){
        log.info("社团更新列表");

        return  ResponseResult.success(clubUpdateService.getUpdate());
    }



}
