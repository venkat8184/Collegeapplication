package com.collegeapplication.repositories;

import com.collegeapplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findEmployeeByDepartmentId(long deptId);
    Employee findOneEmployeeByEmail(String email);
}
