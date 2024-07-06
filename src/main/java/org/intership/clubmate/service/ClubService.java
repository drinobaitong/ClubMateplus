package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Club;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClubService extends IService<Club> {
    void insertClub(Club club);
    void deleteById(Integer id);
    Club getById(Integer id);
    IPage<Club> list(int pageNo,int pageSize);
    void update(Club club);

    IPage<Club> typeList(int pageNo,int pageSize,int type);
    IPage<Club> collageList(int pageNo,int pageSize,String collage);

    List<Club> getClubFS(String search);

    void audit(int status,Integer id);

    void updateImage(Integer clubId, String url);

    void addMember(Integer clubId);
    void subMember(Integer clubId);
}
