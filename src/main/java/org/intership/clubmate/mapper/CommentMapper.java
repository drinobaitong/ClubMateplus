package org.intership.clubmate.mapper;


import org.apache.ibatis.annotations.*;
import org.intership.clubmate.entity.Comment;


import java.util.List;

@Mapper
public interface CommentMapper {

    //获取文章的所有评论
    @Select("select * from comment where article_id=#{articleId}")
    List<Comment> viewArticleComments(Integer articleId);

    //获取某条评论
    @Select("select * from comment where id=#{id}")
    Comment getComment(Integer id);

    //增加评论
    @Insert("insert into comment values (#{id},#{content},#{createUserId},#{articleId},#{layer})")
    int addComment(Comment comment);

    //按id删除评论
    @Delete("delete from comment where id=#{id}")
    int deleteComment(Integer id);

    //获得帖子目前评论数
    @Select("select count(*) from comment where article_id=#{articleId}")
    int getCommentLayersOfArticle(Integer articleId);

    //更新层数
    @Update("update comment set layer=#{layer} where id=#{id}")
    int updateLayer(Integer layer,Integer id);

}
