package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@TableName("department")
public class Department {
    @TableField("name")
    private String name;
    @TableField("president")
    private String president;
    @TableField("register_time")
    private String registerTime;
}
