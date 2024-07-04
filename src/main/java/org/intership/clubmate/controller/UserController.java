package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.intership.clubmate.pojo.ResponseResult;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    /*获取用户
    根据rank,不写rank默认全部用户*/
    @RequestMapping("/getAll")
    public ResponseResult getAllUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam (defaultValue= "3")int rank
                                     ){
        IPage<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (rank == 1||rank==2) {
            queryWrapper.eq(User::getRank,rank);
        }
        IPage<User> userPage= userService.getAll(page,queryWrapper);
        return ResponseResult.success(userPage);
    }

    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody User user){
        User res = userService.addUser(user);
        if(res==null){
            return ResponseResult.error(HttpCode.USERNAME_EXIST);
        }else {
            System.out.println("成功");
            return ResponseResult.success();
        }
    }


    @RequestMapping("/delete")
    public ResponseResult deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return ResponseResult.success();
    }

    @RequestMapping("/update")
    public ResponseResult updateUser(@RequestBody User user){
        User res = userService.updateUser(user);
        if(res!=null){
            return ResponseResult.success(res);
        }else {return ResponseResult.error(HttpCode.USER_NULL);}
    }

    @RequestMapping("/getInfo/{id}")
    public ResponseResult getInfo(@PathVariable int id){
        User res =userService.getById(id);
        if(res!=null){
            return ResponseResult.success(res);
        }else return ResponseResult.error(HttpCode.USER_NULL);
    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user){
        System.out.println(user.getId());
        User res =userService.login(user.getId(),user.getPassword());

        if(res!=null){
            String token = TokenUtils.genToken( String.valueOf(res.getId()),res.getPassword());
            res.setToken(token);
            System.out.println("成功");
            return ResponseResult.success(res);
        }else return ResponseResult.error(HttpCode.LOGIN_ERROR);
    }

    @RequestMapping("/register")
    public ResponseResult register(@RequestBody User user){
        userService.register(user);
        return ResponseResult.success();
    }
}
