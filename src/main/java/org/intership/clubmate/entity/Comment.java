package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "comment")
public class Comment {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//评论id编号

    @TableField("content")
    private String content;//评论内容

    @TableField("create_user_id")
    private Integer createUserId;//发布人id

    @TableField("article_id")
    private Integer articleId;//帖子id

    @TableField("layer")
    private Integer layer;//评论所处层数

    @TableField("register_time")
    private LocalDateTime registerTime;

    @TableField(exist = false)
    private String username;

}
