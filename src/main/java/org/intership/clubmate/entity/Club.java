package org.intership.clubmate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private Integer id;
    private String name;
    private String introduce;
    private Integer createUserId;
    private String avatarUrl;
    private Integer status;
    private LocalDateTime registerTime;
    private Integer totalNumber;
    private String tags;
    private String collage;
}
