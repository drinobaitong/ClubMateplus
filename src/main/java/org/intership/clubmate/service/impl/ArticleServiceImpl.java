package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.mapper.ArticleMapper;
import org.intership.clubmate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public int addArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    @Transactional
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public boolean canDelete(Article article,int create_user_id){
            //创建人权限是否高

           return article.getCreate_user_id() == create_user_id;

    }

    @Override
    @Transactional
    public int deleteArticle(Article article) {
        return articleMapper.deleteArticle(article.getId());
    }


    @Override
    @Transactional
    public List<Article> getAllArticles(){
        return articleMapper.findAllArticles();
    }

    @Override
    public IPage<Article> list(int pageNo, int pageSize) {
        Page<Article> page=new Page<>(pageNo,pageSize);
        return articleMapper.selectPage(page,null);
    }

    @Override
    @Transactional
    public Article getById(int id) {
        return articleMapper.findArticleById(id);
    }

    @Override
    @Transactional
    public List<Article> getArticlesByTitle(String title) {
        return articleMapper.findArticlesByTitle(title);
    }

    @Override
    @Transactional
    public List<Article> getArticlesByClub(int club_id){
        return articleMapper.findArticlesByClub(club_id);
    }

    @Override
    @Transactional
    public List<Article> getArticlesByCreator(int create_user_id){
        return articleMapper.findArticlesByCreator(create_user_id);
    }
}
