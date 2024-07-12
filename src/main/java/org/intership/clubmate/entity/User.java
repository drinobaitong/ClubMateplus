package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@TableName("user")
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
    @TableField(exist = false)
    private String token;
    @TableField("political_affiliation")
    private String politicalAffiliation;
    @TableField("department")
    private String department;
    @TableField("phone")
    private String phone;
    @TableField("email")
    private String email;
}
