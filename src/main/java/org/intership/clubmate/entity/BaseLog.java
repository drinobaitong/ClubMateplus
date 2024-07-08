package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseLog {
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_id")
    private int userId;
    @TableField("ip")
    private String ip;
    @TableField("opera_time")
    private Date operaTime;
    @TableField("opera_request")
    private String operaRequest;
    @TableField("opera_type")
    private String operaType;
    @TableField("opera_url")
    private String operaUrl;
    @TableField("opera_module")
    private String operaModule;

}
