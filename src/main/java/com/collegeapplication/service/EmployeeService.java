package com.collegeapplication.service;

import com.collegeapplication.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createOneEmployeeDto(long departmentId,EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateOneEmployee(long did,long eid,EmployeeDto employeeDto);

   // List<EmployeeDto> getAllEmployees(long id);
    EmployeeDto getOneEmployeeByEmployeeId(long did, long eid);

    //Delete Employee by using DepartmentId and Employee id
    void deleteOneEmployee(long did,long eid);
    List<EmployeeDto> getAllEmployeeByDepartmentId(long id);

    EmployeeDto getOneEmployeeByEmail(long id,String email);
}
