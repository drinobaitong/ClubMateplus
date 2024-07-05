package org.intership.clubmate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.ClubType;
import org.intership.clubmate.mapper.ClubTypeMapper;
import org.intership.clubmate.service.ClubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("clubTypeService")
public class ClubTypeServiceImpl implements ClubTypeService {

    @Autowired
    private ClubTypeMapper clubTypeMapper;
    @Override
    public int addClubType(ClubType clubType) {
        return clubTypeMapper.insertClubType(clubType);
    }

    @Override
    public int deleteClubType(ClubType clubType) {
        return clubTypeMapper.deleteClubType(clubType);
    }

    @Override
    public int modifyClubType(String type,String newtype) {
        return clubTypeMapper.updateClubType(type,newtype);
    }

    @Override
    public ClubType getClubType(String type) {
        return clubTypeMapper.selectClubType(type);
    }
}
