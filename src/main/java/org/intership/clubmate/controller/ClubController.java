package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.intership.clubmate.entity.Club;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClubController {
    @Autowired
    private ClubService clubService;

    //新建社团
    @PostMapping("/club/insert")
    public ResponseResult insertClub(@RequestBody Club club){
        clubService.insertClub(club);
        System.out.println("新建社团");
        return ResponseResult.success();
    }

    //删除社团(同时删除社团内所有文章)
    @DeleteMapping("/club/delete/{id}")
  //  @Transactional
    public ResponseResult deleteClub(@PathVariable Integer id){
        Club club=clubService.getById(id);
        System.out.println("删除社团");
        if(club!=null) {
            //删除社团
            clubService.deleteById(id);
            return ResponseResult.success();
        }else{
            return ResponseResult.setAppHttpCodeEnum(HttpCode.SYSTEM_ERROR,"社团不存在");
        }
    }
    @GetMapping("/club/get/{id}")
    public ResponseResult getClub(@PathVariable Integer id){
        Club club=clubService.getById(id);
        System.out.println("根据id查找社团");
        return  ResponseResult.success(club);
    }

    @GetMapping("/club/list")
    public ResponseResult listClub(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)
    {
        System.out.println("分页查询");
        IPage<Club> clubs=clubService.list(pageNo,pageSize);
        return ResponseResult.success(clubs);
    }

    @GetMapping("/club/list/tags")
    public ResponseResult listByType(
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam (value = "tags")int tags){
        System.out.println("社团分类列表");
        IPage<Club> clubs=clubService.typeList(pageNo,pageSize,tags);
        return ResponseResult.success(clubs);
    }
    @PutMapping("/club/update")
    public ResponseResult updateClub(@RequestBody Club club){

        System.out.println("更新社团");
        clubService.update(club);
        return ResponseResult.success();
    }
}
