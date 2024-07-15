package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.ClubUpdate;

public interface ClubUpdateService extends IService<ClubUpdate> {
    void insertUpdate(ClubUpdate clubUpdate);
    void cancelUpdate(Integer id);

    Club getClubById(Integer id);

    IPage<ClubUpdate> getUpdate();
}
