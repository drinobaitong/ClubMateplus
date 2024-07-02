package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.intership.clubmate.entity.Article;


import java.util.List;


public interface ArticleService {

    //发布文章
    public int addArticle(Article article);

    //更改文章
    public int updateArticle(Article article);

    //删除文章
    public int deleteArticle(Article article);

    //得到文章
    public Article getById(int id);

    //判断是否能删除
    public boolean canDelete(Article article,int create_user_id);

    public List<Article> getAllArticles();
    //根据文章名获取文章
    public List<Article> getArticlesByTitle(String title);

    //根据社团查找文章
    public List<Article> getArticlesByClub(int club_id);

    //根据发布人找文章
    public List<Article> getArticlesByCreator(int create_user_id);

    //分页查询系统用户信息
    IPage<Article> list(int pageNo, int pageSize);

}
