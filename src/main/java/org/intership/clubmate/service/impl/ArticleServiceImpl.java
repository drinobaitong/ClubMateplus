package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.mapper.ArticleMapper;
import org.intership.clubmate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticle(Article article) {
        log.info("发布帖子："+article.getId());
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        log.info("修改帖子："+article.getId());
        return articleMapper.updateArticle(article);
    }


    @Override
    public int deleteArticle(Article article) {
        log.info("删除帖子："+article.getId());
        return articleMapper.deleteArticle(article.getId());
    }

    @Override
    public int setEssenceTrue(Article article) {
        log.info("设置id为"+article.getId()+"号帖子为精华");
        return articleMapper.setEssenceTrue(article);
    }

    @Override
    public int setEssenceFalse(Article article) {
        log.info("取消设置id为"+article.getId()+"号帖子为精华");
        return articleMapper.setEssenceFalse(article);
    }


    @Override
    public List<Article> getAllArticles(){
        log.info("查看所有帖子");
        return articleMapper.findAllArticles();
    }

    @Override
    public List<Article> getArticlesFS(String search){
        log.info("模糊查询帖子");
        return articleMapper.findArticleFS(search);
    }
    @Override
    public IPage<Article> list(int pageNo, int pageSize) {
        log.info("分页展示帖子");
        Page<Article> page=new Page<>(pageNo,pageSize);
        return articleMapper.selectPage(page,null);
    }

    @Override
    public Article getById(Integer id) {
        log.info("获取id为"+id+"的帖子");
        return articleMapper.findArticleById(id);
    }

    @Override
    public IPage<Article> getArticlesListByClub(int pageNo, int pageSize,Integer clubId){
        log.info("分页获取社团id为"+clubId+"的所有帖子");
        Page<Article> page=new Page<>(pageNo,pageSize);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("club_id",clubId);
        return articleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Article> getArticlesByClub(Integer clubId){
        log.info("获取社团id为"+clubId+"的所有帖子");
        return articleMapper.findArticlesByClub(clubId);
    }

//    @Override
//    public List<Article> getArticlesByCreator(Integer createUserId){
//        log.info("获取发布人id为"+createUserId+"的所有帖子");
//        return articleMapper.findArticlesByCreator(createUserId);
//    }

    @Override
    public IPage<Article> getArticlesListByCreator(int pageNo, int pageSize, Integer createUserId) {
        log.info("分页展示发布人id为"+createUserId+"的所有帖子");
        Page<Article> page=new Page<>(pageNo,pageSize);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("create_user_id",createUserId);
        return articleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Article> getEssenceArticles(int pageNo, int pageSize) {
        log.info("获取所有精华帖");
        Page<Article> page=new Page<>(pageNo,pageSize);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("essence",1);
        return articleMapper.selectPage(page,queryWrapper);
    }


}
