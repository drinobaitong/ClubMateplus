package org.intership.clubmate.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Comment;
import org.intership.clubmate.mapper.CommentMapper;
import org.intership.clubmate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByArticle(Integer articleId){
        log.info("获取articleId为"+articleId+"的文章的所有评论");
        return commentMapper.viewArticleComments(articleId);
    }

    @Override
    public Comment getComment(Integer id) {
        log.info("获取id为"+id+"的评论");
        return commentMapper.getComment(id);
    }

    @Override
    public int addComment(Comment comment) {
        log.info("添加评论");
        return commentMapper.addComment(comment);
    }

    @Override
    public int deleteComment(Integer id) {
        log.info("删除评论"+id);
        return commentMapper.deleteComment(id);
    }

    @Override
    @Transactional
    public int getNumOfLayers(Integer articleId){
        log.info("计算帖子"+articleId+"号中评论数。。。。。。");
        return commentMapper.getCommentLayersOfArticle(articleId);
    }


}
