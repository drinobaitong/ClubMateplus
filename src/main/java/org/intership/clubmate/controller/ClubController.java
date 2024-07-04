package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ArticleService;
import org.intership.clubmate.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private ArticleService articleService;
    //新建社团
    @PostMapping("/club/insert")
    public ResponseResult insertClub(@RequestBody Club club){
        clubService.insertClub(club);
        log.info("新建社团");
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
    public ResponseResult updateClub(@RequestBody Club club){

        log.info("更新社团");
        clubService.update(club);
        return ResponseResult.success();
    }

    @PutMapping("/club/audit/{id}")
    public ResponseResult audit(
            @RequestParam int status,
            @PathVariable Integer id
    ){
        clubService.audit(status,id);
        return ResponseResult.success();
    }
}
