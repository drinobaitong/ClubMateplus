package org.intership.clubmate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.intership.clubmate.entity.Department;

public interface DepartmentService extends IService<Department> {
    public Department getDepartment(String name);
    public void delete(String name);

    public Department addDepartment(Department department);
}
