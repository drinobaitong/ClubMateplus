package org.intership.clubmate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubUpdate {
    private Integer id;
    private String name;
    private String introduce;
    private Integer createUserId;
    private String collage;
    private String type;
}
