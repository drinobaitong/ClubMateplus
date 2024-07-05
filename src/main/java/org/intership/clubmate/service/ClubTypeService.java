package org.intership.clubmate.service;

import org.intership.clubmate.entity.ClubType;

public interface ClubTypeService {

    int addClubType(ClubType clubType);

    int deleteClubType(ClubType clubType);

    int modifyClubType(String type,String newtype);

    ClubType getClubType(String type);
}
