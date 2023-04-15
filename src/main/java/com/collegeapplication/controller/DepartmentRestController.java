package com.collegeapplication.controller;


import com.collegeapplication.payload.DepartmentDto;
import com.collegeapplication.payload.DepartmentResponse;
import com.collegeapplication.service.DepartmentService;
import com.collegeapplication.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentRestController {
    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private DepartmentService departmentService;
    //Creating one Department
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmentDto> createOneDepartment(@Valid @RequestBody DepartmentDto deptDto){
        return new ResponseEntity<>(departmentService.saveOneDepartment(deptDto), HttpStatus.CREATED);
    }
    //Deleting Department based on id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneDepartment(@PathVariable("id")long id){
       departmentService.deleteOneDepartment(id);
return new ResponseEntity<>("Department with id is deleted successfully: "+id,HttpStatus.OK);
    }
    //Updating Department based on id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateOneDepartment(@PathVariable("id") long id,
                                                             @RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.updateOneDepartment(id, departmentDto);
   return new ResponseEntity<>(departmentDto1,HttpStatus.OK);
    }
    //Getting the one Department based on id
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getOneDepartment(@PathVariable("id")long id){
    return new ResponseEntity<>(departmentService.getOneDepartment(id),HttpStatus.OK);
    }
    //Getting all Department at once
    @GetMapping
    public ResponseEntity<DepartmentResponse> getAllDepartments(
            @RequestParam(value="pageNo",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue =AppConstants.PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
    ){
        DepartmentResponse allDepartments = departmentService.getAllDepartments(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allDepartments,HttpStatus.OK);
    }
}
