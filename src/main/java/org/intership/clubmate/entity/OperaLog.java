package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("login_log")
public class OperaLog {
    @TableId(value="id",type = IdType.AUTO)
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("ip")
    private String ip;
    @TableField("opera_time")
    private Date operaTime;
    @TableField("opera_request")
    private String operateRequest;
    @TableField("opera_response")
    private String operateResponse;
    @TableField("opera_type")
    private String operaType;
    @TableField("opera_url")
    private String operaUrl;
    @TableField("opera_module")
    private String operaModule;
}
