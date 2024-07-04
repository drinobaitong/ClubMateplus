package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.intership.clubmate.entity.Article;


import java.util.List;


public interface ArticleService {

    //发布帖子
    int addArticle(Article article);

    //更改帖子
    int updateArticle(Article article);

    //删除帖子
    int deleteArticle(Article article);

    //得到帖子
    Article getById(Integer id);

    List<Article> getAllArticles();

    //根据社团查找帖子
    List<Article> getArticlesByClub(Integer clubId);

    //根据发布人找帖子
    List<Article> getArticlesByCreator(Integer createUserId);

    //分页查询帖子
    IPage<Article> list(int pageNo, int pageSize);

}
