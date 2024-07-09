package org.intership.clubmate.controller;

import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.ClubType;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ClubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/clubtype")
public class ClubTypeController {
    @Autowired
    private ClubTypeService clubTypeService;

    @PostMapping("/add")
    public ResponseResult addClubType(String type){
        if(clubTypeService.getClubType(type)!=null){
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"已有此社团类型！请勿重复添加！");
        }else{
            ClubType clubType=new ClubType(type);
            int i=clubTypeService.addClubType(clubType);
            if(i>0)return ResponseResult.success(clubType);
            else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteClubType(String type){
        ClubType clubType=clubTypeService.getClubType(type);
        if(clubType!=null){
            int i= clubTypeService.deleteClubType(clubType);
            if(i>0)return ResponseResult.success(clubType);
            else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"此类别不存在！");
    }

    @PutMapping("/update")
    public ResponseResult updateClubType(String type,String newType){
        ClubType clubType=clubTypeService.getClubType(type);
        if(clubType!=null){
            if(clubTypeService.getClubType(newType)!=null) {
                return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR, "已经存在此类别，无法修改！");
            }
            int i=clubTypeService.modifyClubType(type,newType);
            if(i>0)return ResponseResult.success(clubType);
            else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"要改的类别不存在！");
    }

    @GetMapping("/gettype")
    public ResponseResult getClubType(String type){
        ClubType clubType=clubTypeService.getClubType(type);
        if(clubType!=null){
            return ResponseResult.success(clubType);
        }else return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"找不到此类别！");
    }

}
