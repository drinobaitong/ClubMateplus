package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("login_log")
public class LoginLog {
    @TableField("id")
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("ip")
    private String ip;
    @TableField("login_time")
    private Date loginTime;
    @TableField("operate_request")
    private String operateRequest;
    @TableField("operate_response")
    private String operateResponse;
}
