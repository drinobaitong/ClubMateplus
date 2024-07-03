package org.intership.clubmate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.UserService;
import org.intership.clubmate.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public ResponseResult getAllUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam (defaultValue= "3")int rank
                                     ){
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (rank == 1||rank==2) {
            queryWrapper.eq(User::getRank,rank);
        }
        Page<User> userPage= userService.getAll(page,queryWrapper);
        return ResponseResult.success(userPage);
    }

    @RequestMapping("/add")
    public ResponseResult addUser(@RequestBody User user){
        User res = userService.addUser(user);
        if(res!=null){
            //System.out.println("成功");
            return ResponseResult.success();
        }else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
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
        }else return ResponseResult.error(HttpCode.SYSTEM_ERROR);
    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestParam int id,@RequestParam String password){
        User user1 = userService.login(id, password);
        if (user1 != null) {
            String token = TokenUtils.genToken( String.valueOf(user1.getId()),user1.getPassword());
            user1.setToken(token);
            return ResponseResult.success(user1);
        } else {
            return ResponseResult.error(HttpCode.LOGIN_ERROR);
        }
    }

    @RequestMapping("/register")
    public ResponseResult register(@RequestBody User user){
        userService.register(user);
        return ResponseResult.success();
    }

    @RequestMapping("/getInfo/{id}")
    public ResponseResult getInfo(@PathVariable int id){
        User user=userService.getById(id);
        if(user!=null){
            return ResponseResult.success(user);
        }else return ResponseResult.error(HttpCode.USER_NULL);
    }
}
