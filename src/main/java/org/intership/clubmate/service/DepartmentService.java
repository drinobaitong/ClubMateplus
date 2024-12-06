package org.intership.clubmate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {
    public Department getDepartment(String name);
    public void delete(String name);

    public Department addDepartment(Department department);

    public List<Department> getAll();
}
