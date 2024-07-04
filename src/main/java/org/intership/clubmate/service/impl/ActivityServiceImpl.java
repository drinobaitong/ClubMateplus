package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.Activity;
import org.intership.clubmate.mapper.ActivityMapper;
import org.intership.clubmate.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public void insertActivity(Activity activity) {
        activity.setCreateTime(LocalDateTime.now());
        activity.setStatus(0);
        activityMapper.insert(activity);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityMapper.deleteById(id);
    }

    @Override
    public Activity getById(Integer id) {
        return activityMapper.selectById(id);
    }

    @Override
    public IPage<Activity> listByCollage(int pageNo,int pageSize,String collage) {
        Page<Activity> page =new Page<>(pageNo,pageSize);
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("collage",collage);
        return activityMapper.selectPage(page,queryWrapper);
    }
}
