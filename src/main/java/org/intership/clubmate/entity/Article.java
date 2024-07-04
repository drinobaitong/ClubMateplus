package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

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
    @TableField("register_time")
    private LocalDateTime registerTime;   //发布时间


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createUserId=" + createUserId +
                ", clubId=" + clubId +
                ", status=" + status +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }


}
