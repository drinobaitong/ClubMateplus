package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user_club_collection")
public class UserClubCollection {
    @TableField("user_id")
    private int userId;
    @TableField("club_id")
    private int clubId;
}
