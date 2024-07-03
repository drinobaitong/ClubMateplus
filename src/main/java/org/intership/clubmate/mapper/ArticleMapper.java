package org.intership.clubmate.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.intership.clubmate.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from article")
    List<Article> findAllArticles();//预览

    @Select("select * from article where id=#{id}")
    Article findArticleById(Integer id);//单个Article展示

    @Select("select * from article where club_id=#{clubId};")
    List<Article> findArticlesByClub(Integer clubId);//按社团找文章

    @Select("select * from article where create_user_id=#{createUserId};")
    List<Article> findArticlesByCreator(Integer createUserId);

    @Insert("insert into article values (#{id},#{title},#{content},#{createUserId},#{clubId},#{status},#{registerTime});")
    int insertArticle(Article article);//发布新的Article

    @Update("update article set title=#{title},content=#{content},register_Time=#{registerTime} where id=#{id}")
    int updateArticle(Article article);//更改Article并重新发布

    @Delete("delete from article where id=#{id}")
    int deleteArticle(int id);//删除某个Article



}
