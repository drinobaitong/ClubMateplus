package org.intership.clubmate.controller;

import org.intership.clubmate.entity.Comment;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //查看帖子所有评论
    @GetMapping("/{articleId}")
    public ResponseResult viewArticleComments(@PathVariable("articleId")Integer articleId){
        List<Comment> comments=commentService.getCommentsByArticle(articleId);
        if(comments.isEmpty()){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"暂无评论！");
        }else return ResponseResult.success(comments);
    }

    //增加评论
    @PostMapping("/add")
    public ResponseResult addComment(Comment comment){
        Integer articleId=comment.getArticleId();
        Integer layer=commentService.getNumOfLayers(articleId);
        comment.setLayer(layer+1);
        int i=commentService.addComment(comment);
        if(i>0)return ResponseResult.success(comment);
        else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"无法评论！");

    }

    //删除评论
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteComment(@PathVariable("id")Integer id){
        Comment comment=commentService.getComment(id);
        if(comment!=null){
            commentService.deleteComment(id);
            return ResponseResult.success(comment);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"删除失败！");
    }
}
