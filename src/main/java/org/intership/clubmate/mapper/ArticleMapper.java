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
    Article findArticleById(Integer id);//单个帖子展示

    @Select("select * from article where club_id=#{clubId};")
    List<Article> findArticlesByClub(Integer clubId);//按社团找帖子

    @Select("select * from article where create_user_id=#{createUserId};")
    List<Article> findArticlesByCreator(Integer createUserId);

    @Select("select * from article where essence=1;")
    List<Article> findEssenceArticles();//查看所有精华帖

    @Insert("insert into article values (#{id},#{title},#{content},#{createUserId},#{clubId},#{status},#{avatarUrl},#{essence},#{registerTime});")
    int insertArticle(Article article);//发布新的帖子

    @Update("update article set title=#{title},content=#{content},avatar_url=#{avatarUrl},register_Time=#{registerTime} where id=#{id}")
    int updateArticle(Article article);//更改帖子并重新发布

    @Update("update article set essence=1 where id=#{id}")
    int setEssenceTrue(Article article);//设置为精华

    @Update("update article set essence=0 where id=#{id}")
    int setEssenceFalse(Article article);//取消设置精华

    @Delete("delete from article where id=#{id}")
    int deleteArticle(int id);//删除某个帖子



}
