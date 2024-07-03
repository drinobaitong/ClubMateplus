package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.intership.clubmate.entity.Article;


import java.util.List;


public interface ArticleService {

    //发布文章
    int addArticle(Article article);

    //更改文章
    int updateArticle(Article article);

    //删除文章
    int deleteArticle(Article article);

    //得到文章
    Article getById(Integer id);


    List<Article> getAllArticles();

    //根据社团查找文章
    List<Article> getArticlesByClub(Integer clubId);

    //根据发布人找文章
    List<Article> getArticlesByCreator(Integer createUserId);

    //分页查询系统用户信息
    IPage<Article> list(int pageNo, int pageSize);

}
