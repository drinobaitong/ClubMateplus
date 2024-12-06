package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName(value="club_type")
public class ClubType {
    @TableId("type")
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "ClubType{" +
                "Type='" + Type + '\'' +
                '}';
    }

    public ClubType() {
    }

    public ClubType(String type) {
        Type = type;
    }
}
