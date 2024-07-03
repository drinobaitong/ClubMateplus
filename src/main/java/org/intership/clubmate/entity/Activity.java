package org.intership.clubmate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private int id;
    private int createUserId;
    private int clubId;
    private String name;
    private String content;
    private LocalDateTime createTime;
    private String avatar;
    private int status;
}
