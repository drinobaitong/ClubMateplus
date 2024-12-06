package org.intership.clubmate.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("error_log")
public class ErrorLog extends BaseLog {

    @TableField("err_code")
    private int errCode;
    @TableField("err_message")
    private String errMessage;

}
