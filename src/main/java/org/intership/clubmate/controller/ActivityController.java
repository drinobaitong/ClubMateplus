package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.Activity;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("activity/list/collage")
    public ResponseResult listByCollage(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "collage") String collage
    ){
        log.info("根据学院分类活动");
        IPage<Activity> activities=activityService.listByCollage(pageNo,pageSize,collage);
        return ResponseResult.success(activities);
    }
}
