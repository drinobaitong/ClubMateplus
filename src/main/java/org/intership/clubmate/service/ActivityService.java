package org.intership.clubmate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Activity;

public interface ActivityService extends IService<Activity> {
    void insertActivity(Activity activity);
    void deleteActivity(Integer id);
    Activity getById(Integer id);
    IPage<Activity> listByCollage(int pageNo,int pageSize,String collage);
}
