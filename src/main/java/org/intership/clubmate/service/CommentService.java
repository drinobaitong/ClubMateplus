package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.aspectj.apache.bcel.generic.IINC;

import org.intership.clubmate.entity.Comment;

import java.util.List;

public interface CommentService {

    //获取文章所有评论
    List<Comment> getCommentsByArticle(Integer articleId);

    //获取某条评论
    Comment getComment(Integer id);

    //获取所有评论
    IPage<Comment> getAllComments(IPage<Comment> page, Wrapper<Comment> queryWrapper);
    //增加评论
    int addComment(Comment comment);

    //删除评论
    int deleteComment(Integer id);

    //获取楼数
    int getNumOfLayers(Integer articleId);

    List<Comment> getCommentsByCLub(Integer clubId);
}
