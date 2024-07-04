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

    //按id查看帖子
    @GetMapping("/{id}")
    public ResponseResult viewArticleDetails(@PathVariable("id") Integer id){
        Article article=articleService.getById(id);
        if(article!=null){
            return ResponseResult.success(article);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");

    }

    //按发布人查看帖子
    @GetMapping("/creator/{createUserId}")
    public ResponseResult viewArticlesByCreator(@PathVariable("createUserId")Integer createUserId){
        List<Article> articles=articleService.getArticlesByCreator(createUserId);
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //按社团查看帖子
    @GetMapping("/club/{clubId}")
    public ResponseResult viewArticlesByClub(@PathVariable("clubId")Integer clubId){
        List<Article> articles=articleService.getArticlesByClub(clubId);
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //分页列出帖子
    @GetMapping("/all/list")
    public ResponseResult listClub(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        IPage<Article> articles=articleService.list(pageNo,pageSize);
        return ResponseResult.success(articles);
    }

    //列出精华帖
    @GetMapping("/essence/all")
    public ResponseResult viewEssenceArticles(){
        List<Article> articles=articleService.getEssenceArticles();
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }


    //列出全部帖子
    @GetMapping("/all")
    public ResponseResult viewAllArticles(){
        List<Article> articles=articleService.getAllArticles();
        if(articles.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"未找到文章！");
        }else return ResponseResult.success(articles);
    }

    //修改帖子
    @PostMapping("/update/{id}")
    public ResponseResult modifyArticle(@PathVariable("id")Integer id,String title,String content,String avatarUrl){
        Article article=articleService.getById(id);
        if(article!=null){
            article.setContent(content);
            article.setTitle(title);
            article.setAvatarUrl(avatarUrl);
            article.setRegisterTime(getDateTime());
            articleService.updateArticle(article);
            return ResponseResult.success(article);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"更新失败！");

        //可能需要细化？
    }

    //发布帖子
    @PostMapping("/publish")
    public ResponseResult addArticle(Article article){
        article.setEssence(0);
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

    //设置精华
    @PostMapping("/setet/{id}")
    public ResponseResult setEssenceTrue(@PathVariable("id")Integer id){
        Article article=articleService.getById(id);
        if(article!=null){
            if(article.getEssence()==1){
                return ResponseResult.setAppHttpCodeEnum(HttpCode.SUCCESS,"帖子已经是精华帖了！");
            }else{
                article.setEssence(1);
                articleService.setEssenceTrue(article);
                return ResponseResult.success(article);
            }
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"文章不存在！");
    }

    //取消设置精华
    @PostMapping("/setef/{id}")
    public ResponseResult setEssenceFalse(@PathVariable("id")Integer id){
        Article article=articleService.getById(id);
        if(article!=null){
            if(article.getEssence()==0){
                return ResponseResult.setAppHttpCodeEnum(HttpCode.SUCCESS,"帖子已经不是精华帖了！");
            }else {
                article.setEssence(0);
                articleService.setEssenceFalse(article);
                return ResponseResult.success(article);
            }
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"文章不存在！");
    }


    public LocalDateTime getDateTime(){
        LocalDateTime localDateTime=LocalDateTime.now();
        return localDateTime;
    }

}
