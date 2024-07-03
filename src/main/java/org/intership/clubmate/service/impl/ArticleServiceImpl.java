package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.intership.clubmate.entity.Article;
import org.intership.clubmate.mapper.ArticleMapper;
import org.intership.clubmate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }


    @Override
    public int deleteArticle(Article article) {
        return articleMapper.deleteArticle(article.getId());
    }


    @Override
    public List<Article> getAllArticles(){
        return articleMapper.findAllArticles();
    }

    @Override
    public IPage<Article> list(int pageNo, int pageSize) {
        Page<Article> page=new Page<>(pageNo,pageSize);
        return articleMapper.selectPage(page,null);
    }

    @Override
    public Article getById(Integer id) {
        return articleMapper.findArticleById(id);
    }

    @Override
    public List<Article> getArticlesByClub(Integer clubId){
        return articleMapper.findArticlesByClub(clubId);
    }

    @Override
    public List<Article> getArticlesByCreator(Integer createUserId){
        return articleMapper.findArticlesByCreator(createUserId);
    }
}
