package com.collegeapplication.repositories;

import com.collegeapplication.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
