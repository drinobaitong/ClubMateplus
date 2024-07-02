package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

@TableName(value = "article")
public class Article {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;   //ID编号
    @TableField("title")
    private String title;  //标题
    @TableField("content")
    private String content;  //内容
    @TableField("create_user_id")
    private int create_user_id;  //创建者ID
    @TableField("club_id")
    private int club_id;//社团id
    @TableField("status")
    private char status;   //状态
    @TableField("register_time")
    private Timestamp register_time;   //发布时间

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_user_id=" + create_user_id +
                ", club_id=" + club_id +
                ", status=" + status +
                ", register_time='" + register_time + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }
}
