package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.intership.clubmate.config.Log;
import org.intership.clubmate.entity.ErrorLog;
import org.intership.clubmate.entity.OperaLog;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.enums.OperaType;
import org.intership.clubmate.service.ErrorLogService;
import org.intership.clubmate.service.OperaLogService;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;

import org.springframework.web.bind.annotation.*;
import org.intership.clubmate.pojo.ResponseResult;

import java.util.Date;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    /*获取用户
    根据rank,不写rank默认全部用户*/
    @RequestMapping("/getAll")
    @Log(operaModule = "获取用户列表",operaType = "GET")
    public ResponseResult getAllUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam (defaultValue= "3")int rank
                                     ){
        IPage<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (rank==0||rank == 1||rank==2) {
            queryWrapper.eq(User::getRank,rank);
        }
        IPage<User> userPage= userService.getAll(page,queryWrapper);
        return ResponseResult.success(userPage);
    }

    @PostMapping("/add")
    @Log(operaModule = "新增用户",operaType = "ADD")
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
    @Log(operaModule = "删除用户",operaType = "DELETE")
    public ResponseResult deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return ResponseResult.success();
    }

    @RequestMapping("/update")
    @Log(operaModule = "更新用户信息",operaType = "UPDATE")
    public ResponseResult updateUser(@RequestBody User user){
        User res = userService.updateUser(user);
        if(res!=null){
            return ResponseResult.success(res);
        }else {return ResponseResult.error(HttpCode.USER_NULL);}
    }

    @RequestMapping("/getInfo/{id}")
    @Log(operaModule = "获取用户信息",operaType = "GET")
    public ResponseResult getInfo(@PathVariable int id){
        User res =userService.getById(id);
        if(res!=null){
            return ResponseResult.success(res);
        }else return ResponseResult.error(HttpCode.USER_NULL);
    }

    @RequestMapping("/login")
    @Log(operaModule = "登录",operaType = "GET")
    public ResponseResult login(@RequestBody User user){
        System.out.println(user.getId());
        User res =userService.login(user.getId(),user.getPassword());

        if(res!=null){
            String token = TokenUtils.genToken( String.valueOf(res.getId()),res.getPassword());
            res.setToken(token);
            System.out.println("成功");

            return ResponseResult.success(res);
        }else{

            return ResponseResult.error(HttpCode.LOGIN_ERROR);}
    }

    @RequestMapping("/register")
    @Log(operaModule = "注册",operaType = "ADD")
    public ResponseResult register(@RequestBody User user){
        User res =userService.getById(user.getId());
        if(res!=null){
            return ResponseResult.error(HttpCode.USERNAME_EXIST);
        }
        res =userService.register(user);
        if (res==null){
            return ResponseResult.error(HttpCode.LOGIN_ERROR.hashCode(),"密码不符合格式");
        }
        return ResponseResult.success();
    }

    @RequestMapping("/changeRank")
    public ResponseResult changeRank(@RequestParam int id,@RequestParam int rank){
       User user= userService.getById(id);
       user.setRank(rank);
       User res=userService.updateUser(user);
       if(res!=null){
           return ResponseResult.success(res);
       }return ResponseResult.error(HttpCode.USER_NULL);
    }

}
