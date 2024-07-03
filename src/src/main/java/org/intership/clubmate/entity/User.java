package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Getter
@Setter
@TableName("user")
@Accessors(chain = true)
public class User  {
    @TableField("id")
    private int id;
    @TableField("name")
    private String name;
    @TableField("password")
    private String password;
    @TableField("avatar_url")
    private String avatarUrl;
    @TableField("sex")
    private int sex;
    @TableField("`rank`")
    private int rank;
    @TableField("introduce")
    private String introduce;
}
