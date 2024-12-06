package org.intership.clubmate.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.intership.clubmate.config.Log;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.entity.UserClubCollection;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ClubService;
import org.intership.clubmate.service.UserClubCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/collection")
public class UserClubCollectionController {

    @Autowired
    private UserClubCollectionService userClubCollectionService;
    @Autowired
    private ClubService clubService;

    @RequestMapping("/add")
    @Log(operaModule = "添加收藏",operaType = "ADD")
    public ResponseResult addCollection(@RequestBody UserClubCollection userClubCollection){
        UserClubCollection ucc= userClubCollectionService.add(userClubCollection);
        if(ucc==null){
            return ResponseResult.error(HttpCode.COLLECTION_NOT_NULL);
        }
        return ResponseResult.success();
    }

    @RequestMapping("/delete")
    @Log(operaModule = "取消收藏",operaType = "DELETE")
    public ResponseResult deleteCollection(@RequestBody UserClubCollection userClubCollection){
        userClubCollectionService.delete(userClubCollection.getUserId(),userClubCollection.getClubId());
        UserClubCollection ucc =userClubCollectionService.findUserClubCollection(userClubCollection.getUserId(),userClubCollection.getClubId());
        if(ucc==null){
            return ResponseResult.success();
        }
        else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
    }

    //感觉没用
    @RequestMapping("/getCollection/{clubId}")
    @Log(operaModule = "获取收藏的社团",operaType = "GET")
    public ResponseResult getCollection(@RequestParam int uid,@PathVariable int clubId){
        UserClubCollection ucc =userClubCollectionService.findUserClubCollection(uid, clubId);
        if(ucc!=null){
           Club club = clubService.getById(ucc.getClubId());
           return ResponseResult.success(club);
        }else return ResponseResult.error(HttpCode.COLLECTION_NULL);
    }

    @RequestMapping("/getAll")
    @Log(operaModule = "获取收藏列表",operaType = "GET")
    public ResponseResult getALl(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam int uid){
        UserClubCollection res =userClubCollectionService.getBaseMapper().selectOne(Wrappers.<UserClubCollection>lambdaQuery().eq(UserClubCollection::getUserId,uid));
        if(res==null){
            return ResponseResult.error(HttpCode.USER_NULL);
        }
        IPage<Club> clubs=userClubCollectionService.getClub(new Page<>(pageNum, pageSize),uid);
        return ResponseResult.success(clubs);
    }


    //判断是否收藏对应社团
    @GetMapping("/getcollsta")
    @Log(operaModule = "获取收藏状态",operaType = "GET")
    public ResponseResult isCollect(@RequestParam Integer userId,
                                    @RequestParam Integer clubId){
        UserClubCollection userClubCollection=userClubCollectionService.findUserClubCollection(userId,clubId);
        if(userClubCollection!=null){
            return ResponseResult.success(true);
        }else return ResponseResult.error(HttpCode.COLLECTION_NULL);
    }


}
