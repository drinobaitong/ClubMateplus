package org.intership.clubmate.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.intership.clubmate.entity.Department;
import org.intership.clubmate.entity.UserClubCollection;
import org.intership.clubmate.mapper.DepartmentMapper;
import org.intership.clubmate.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public Department getDepartment(String name) {
        return departmentMapper.selectOne((Wrappers.<Department>lambdaQuery().
                eq(Department::getName,name)));
    }

    @Override
    public void delete(String name) {
        departmentMapper.delete((Wrappers.<Department>lambdaQuery().
                eq(Department::getName,name)));
    }

    @Override
    public Department addDepartment(Department department) {
        Department res= departmentMapper.selectOne((Wrappers.<Department>lambdaQuery().
                eq(Department::getName,department.getName())));
        if(res!=null){
            return null;
        }
        else
            departmentMapper.insert(department);
        return department;
    }

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectList((Wrappers.<Department>lambdaQuery()));
    }
}
