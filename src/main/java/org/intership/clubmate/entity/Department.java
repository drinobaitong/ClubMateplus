package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("department")
public class Department {
    @TableField("name")
    private String name;
    @TableField("president")
    private String president;
    @TableField("register_time")
    private String registerTime;
}
