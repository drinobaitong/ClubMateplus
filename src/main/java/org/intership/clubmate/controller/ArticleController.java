package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //按id查看文章
    @GetMapping("/{id}")
    public ResponseResult viewArticleDetails(@PathVariable("id") int id){
        Article article=articleService.getById(id);
        return ResponseResult.success(article);
    }

    //按发布人查看文章
    @GetMapping("/creator/{create_user_id}")
    public ResponseResult viewArticlesByCreator(@PathVariable("create_user_id")int create_user_id){
        List<Article> articles=articleService.getArticlesByCreator(create_user_id);
        return ResponseResult.success(articles);
    }

    //按社团查看文章
    @GetMapping("/club/{club_id}")
    public ResponseResult viewArticlesByClub(@PathVariable("club_id")int club_id){
        List<Article> articles=articleService.getArticlesByClub(club_id);
        return ResponseResult.success(articles);
    }

//    //按标题列出文章
//    @GetMapping("/title/{title}")
//    public List<Article> viewArticlesByTitle(@PathVariable("title")String title){
//        return articleService.getArticlesByTitle(title);
//    }

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
        return ResponseResult.success(articles);
    }

    //修改文章
    @PostMapping("/update/{id}")
    public ResponseResult modifyArticle(@PathVariable("id")int id,String title,String content){
        Article article=articleService.getById(id);
        article.setContent(content);
        article.setTitle(title);
        article.setRegister_time(getDateTime());
        int i=articleService.updateArticle(article);
        if(i>0)return ResponseResult.success(article);
        else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        //可能需要细化？

    }

    //发布文章
    @PostMapping("/publish")
    public ResponseResult addArticle(Article article){
        article.setRegister_time(getDateTime());
        int i=articleService.addArticle(article);
        if(i>0)return ResponseResult.success(article);
        else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        //需要细化？
    }

    //删除文章
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteArticle(@PathVariable("id")int id){
        Article article=articleService.getById(id);
        if(article!=null){
            articleService.deleteArticle(article);
            return ResponseResult.success(article);

        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"文章不存在！");

    }


    public Timestamp getDateTime(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

}
