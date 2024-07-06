package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.intership.clubmate.entity.ErrorLog;
import org.intership.clubmate.entity.LoginLog;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.service.ErrorLogService;
import org.intership.clubmate.service.LoginLogService;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;

import org.springframework.web.bind.annotation.*;
import org.intership.clubmate.pojo.ResponseResult;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private ErrorLogService errorLogService;
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
            LoginLog loginLog =new LoginLog();
            loginLog.setUserId(user.getId());
            loginLog.setLoginTime(new Date());
            loginLog.setIp(request.getRemoteAddr());
            //将参数所在的数组转为json
            loginLog.setOperateRequest(JSON.toJSONString(user));
            loginLog.setOperateResponse(JSON.toJSONString(res));
            loginLogService.add(loginLog);
            return ResponseResult.success(res);
        }else{
            ErrorLog errorLog=new ErrorLog();
            errorLog.setUserId(user.getId());
            errorLog.setOperaIp(request.getRemoteAddr());
            errorLog.setOperaTime(new Date());
            errorLog.setErrMessage("账号密码有误");
            errorLog.setOperaMethod("登录");
            errorLog.setErrCode("503");
            errorLogService.add(errorLog);
            return ResponseResult.error(HttpCode.LOGIN_ERROR);}
    }

    @RequestMapping("/register")
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


}
