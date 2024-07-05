package org.intership.clubmate.controller;


import org.intership.clubmate.entity.Department;
import org.intership.clubmate.enums.HttpCode;
import org.intership.clubmate.pojo.ResponseResult;
import org.intership.clubmate.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
