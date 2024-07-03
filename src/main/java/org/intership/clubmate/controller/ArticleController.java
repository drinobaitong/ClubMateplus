package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //按id查看文章
    @GetMapping("/{id}")
    public ResponseResult viewArticleDetails(@PathVariable("id") Integer id){
        Article article=articleService.getById(id);
        if(article!=null){
            return ResponseResult.success(article);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");

    }

    //按发布人查看文章
    @GetMapping("/creator/{createUserId}")
    public ResponseResult viewArticlesByCreator(@PathVariable("createUserId")Integer createUserId){
        List<Article> articles=articleService.getArticlesByCreator(createUserId);
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //按社团查看文章
    @GetMapping("/club/{clubId}")
    public ResponseResult viewArticlesByClub(@PathVariable("clubId")Integer clubId){
        List<Article> articles=articleService.getArticlesByClub(clubId);
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //分页列表
    @GetMapping("/all/list")
    public ResponseResult listClub(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        IPage<Article> articles=articleService.list(pageNo,pageSize);
        return ResponseResult.success(articles);
    }

    //列出全部文章
    @GetMapping("/all")
    public ResponseResult viewAllArticles(){
        List<Article> articles=articleService.getAllArticles();
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //修改文章
    @PostMapping("/update/{id}")
    public ResponseResult modifyArticle(@PathVariable("id")Integer id,String title,String content){
        Article article=articleService.getById(id);
        if(article!=null){
            article.setContent(content);
            article.setTitle(title);
            article.setRegisterTime(getDateTime());
            articleService.updateArticle(article);
            return ResponseResult.success(article);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"更新失败！");

        //可能需要细化？
    }

    //发布文章
    @PostMapping("/publish")
    public ResponseResult addArticle(Article article){
        article.setRegisterTime(getDateTime());
        int i=articleService.addArticle(article);
        if(i>0)return ResponseResult.success(article);
        else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        //需要细化？
    }

    //删除文章
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteArticle(@PathVariable("id")Integer id){
        Article article=articleService.getById(id);
        if(article!=null){
            articleService.deleteArticle(article);
            return ResponseResult.success(article);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"文章不存在！");

    }


    public LocalDateTime getDateTime(){
        LocalDateTime localDateTime=LocalDateTime.now();
        return localDateTime;
    }

}
