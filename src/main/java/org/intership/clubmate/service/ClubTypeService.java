package org.intership.clubmate.service;

import org.intership.clubmate.entity.ClubType;

import java.util.List;

public interface ClubTypeService {

    int addClubType(ClubType clubType);

    int deleteClubType(ClubType clubType);

    int modifyClubType(String type,String newtype);

    ClubType getClubType(String type);

    List<ClubType> getALl();
}
