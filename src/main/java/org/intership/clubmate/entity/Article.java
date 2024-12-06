package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "article")
public class Article {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;   //ID编号
    @TableField("title")
    private String title;  //标题
    @TableField("content")
    private String content;  //内容
    @TableField("create_user_id")
    private Integer createUserId;  //创建者ID
    @TableField("club_id")
    private Integer clubId;//社团id
    @TableField("status")
    private char status;   //状态
    @TableField("avatar_url")
    private String avatarUrl;

    @TableField("essence")
    private Integer essence;//精华

    @TableField("register_time")
    private LocalDateTime registerTime;   //发布时间
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private Club club;

}
