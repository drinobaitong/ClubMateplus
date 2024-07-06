package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.intership.clubmate.entity.UCJoin;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ClubService;
import org.intership.clubmate.service.UCJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UCJoinController {
    @Autowired
    private UCJoinService ucJoinService;
    @Autowired
    private ClubService clubService;
    //申请入社
    @PostMapping("/join")
    public ResponseResult joinClub(
            @RequestParam Integer userId,
            @RequestParam Integer clubId){
        ucJoinService.insert(userId,clubId);
        return ResponseResult.success();
    }

    //获取社团中所有成员id以及状态
    @GetMapping("/club/users/{clubId}")
    public ResponseResult getUsers(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable Integer clubId
    ){
        log.info("获取所有Id状态");
        IPage<UCJoin> ucs= ucJoinService.getUsers(pageNo,pageSize,clubId);

        return ResponseResult.success(ucs);
    }
    @GetMapping("/join/users/{clubId}")
    public ResponseResult getJoins(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable Integer clubId
    ){
        log.info("获取所有Id状态");
        IPage<UCJoin> ucs= ucJoinService.getJoins(pageNo,pageSize,clubId);

        return ResponseResult.success(ucs);
    }
    @GetMapping("/quit/users/{clubId}")
    public ResponseResult getQuits(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable Integer clubId
    ){
        log.info("获取所有Id状态");
        IPage<UCJoin> ucs= ucJoinService.getQuits(pageNo,pageSize,clubId);

        return ResponseResult.success(ucs);
    }
    //退出社团
    @DeleteMapping("/join/quit")
    public ResponseResult quit(
            @RequestParam Integer userId,
            @RequestParam Integer clubId
    ){
        log.info("成员退出社团");
        ucJoinService.quit(userId,clubId);
        return ResponseResult.success();
    }

    //审核成员
    @PutMapping("/join/audit")
    public ResponseResult audit(
            @RequestParam Integer userId,
            @RequestParam Integer clubId,
            @RequestParam int status
    ){
        log.info("审核社团成员");

        int theStatus=ucJoinService.getStatus(clubId,userId);
        //判断是入社还是退社
        if(theStatus==1){
            //退社
            if (status==1){
                clubService.subMember(clubId);
                ucJoinService.audit(userId,clubId,3);
            }
        }
        else {
            //不是社团成员，入社
            if (status==1){
                clubService.addMember(clubId);
                ucJoinService.audit(userId,clubId,2);
            }
        }
        return ResponseResult.success();
    }

    //获取用户所有加入的社团(id)
    @GetMapping("/join/club/{userId}")
    public ResponseResult getClubs(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @PathVariable Integer userId
    ){
        log.info("获取所有Id状态");
        IPage<UCJoin> ucs= ucJoinService.getClubs(pageNo,pageSize,userId);

        return ResponseResult.success(ucs);
    }
}
