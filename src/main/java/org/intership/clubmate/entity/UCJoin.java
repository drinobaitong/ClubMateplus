package org.intership.clubmate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_club_join")
public class UCJoin {
    private Integer userId;
    private Integer clubId;
    private int status;
    private LocalDateTime joinTime;
    private int rank;
}
