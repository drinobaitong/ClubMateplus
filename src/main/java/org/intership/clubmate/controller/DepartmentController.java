package org.intership.clubmate.controller;


import org.intership.clubmate.entity.Department;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/add")
    public ResponseResult addDepartment(@RequestBody Department department){
        Department res=departmentService.addDepartment(department);
        if(res!=null){
            return ResponseResult.success();
        }
        return ResponseResult.error(HttpCode.SYSTEM_ERROR);
    }

    @RequestMapping("/delete/{name}")
    public ResponseResult deletDepartment(@PathVariable String name){
        departmentService.delete(name);
        return ResponseResult.success();
    }

    @RequestMapping("/get/{name}")
    public ResponseResult getDepartment(@PathVariable String name){
        Department res=departmentService.getDepartment(name);
        if(res==null){
            return ResponseResult.error(HttpCode.SYSTEM_ERROR);
        }
        return ResponseResult.success(res);
    }

    @RequestMapping("/getAll")
    public ResponseResult getAll(){
        List<Department> departmentList =departmentService.getAll();
        return ResponseResult.success(departmentList);
    }
}
