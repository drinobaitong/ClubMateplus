package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("error_log")
public class ErrorLog {
    @TableField("id")
    private int id;
    @TableField("err_code")
    private String errCode;
    @TableField("err_message")
    private String errMessage;
    @TableField("user_id")
    private int userId;
    @TableField("opera_method")
    private String operaMethod;
    @TableField("opera_ip")
    private String operaIp;
    @TableField("opera_time")
    private Date operaTime;
}
