package com.collegeapplication.service;

import com.collegeapplication.payload.DepartmentDto;
import com.collegeapplication.payload.DepartmentResponse;

public interface DepartmentService {
    public DepartmentDto saveOneDepartment(DepartmentDto deptDto);
    public void deleteOneDepartment(long id);
    public DepartmentDto updateOneDepartment(long id, DepartmentDto departmentDto);
    public DepartmentDto getOneDepartment(long id);
    DepartmentResponse getAllDepartments(int pageNo, int pageSize, String sortBy,String sortDir);
}
