package com.collegeapplication.service.impl;

import com.collegeapplication.entities.Department;
import com.collegeapplication.exception.ResourceNotFoundException;
import com.collegeapplication.payload.DepartmentDto;
import com.collegeapplication.payload.DepartmentResponse;
import com.collegeapplication.repositories.DepartmentRepository;
import com.collegeapplication.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository deptRepository;


    @Autowired
    private ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository deptRepository)
    {
        this.deptRepository = deptRepository;
    }
//Creating New Department
    @Override
    public DepartmentDto saveOneDepartment(DepartmentDto deptDto) {
        Department department = modelMapper.map(deptDto, Department.class);
        Department department1 = deptRepository.save(department);
        return modelMapper.map(department1,DepartmentDto.class);
    }
//Deleting the department based on the Department Id
    @Override
    public void deleteOneDepartment(long id) {
        Department department = deptRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Department", "DeptId", id));
        deptRepository.delete(department);
    }
/*Getting Department entity based on dept id , updating the Department
   and Returning Department Dto */
    @Override
    public DepartmentDto updateOneDepartment(long id,DepartmentDto departmentDto) {
        //getting Department Entity based on Id
        Department department = deptRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department","DeptId",id));
        //Converting Department Entity to Department Dto
        DepartmentDto departmentDto1 =modelMapper.map(department,DepartmentDto.class);
        //Setting the existing deptment Dto values with new Department dto values
        departmentDto1.setDeptNo(departmentDto.getDeptNo());
        departmentDto1.setDeptName(departmentDto.getDeptName());
        departmentDto1.setDeptSize(departmentDto.getDeptSize());
        //Converting Department Dto to Department Entity
        Department department1 =  modelMapper.map(departmentDto1,Department.class);
        //Saving the Department Entity
        Department dept1 = deptRepository.save(department1);
        //Converting the Department Entity to Department Dto
        return modelMapper.map(dept1,DepartmentDto.class);
    }
    @Override
    public DepartmentDto getOneDepartment(long id) {
        Department department = deptRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "DeptId", id));
        return mapToDto(department);
    }

    @Override
    public DepartmentResponse getAllDepartments(int pageNo, int pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable =PageRequest.of(pageNo,pageSize,sort );
        Page<Department> pages = deptRepository.findAll(pageable);
        List<Department> contents = pages.getContent();
        List<DepartmentDto> departments = contents.stream().map(content -> modelMapper.map(content, DepartmentDto.class)).collect(Collectors.toList());
      DepartmentResponse response=new DepartmentResponse();
      response.setContents(departments);
      response.setPageNumber(pages.getNumber());
      response.setPageSize(pages.getSize());
      response.setTotalPages(pages.getTotalPages());
      response.setTotalElements(pages.getTotalElements());
      response.setLast(pages.isLast());
        return response;
    }

    //Converting Department Entity to DepartmentDto
    private DepartmentDto mapToDto(Department department) {
        DepartmentDto dto1=new DepartmentDto();
        dto1.setId(department.getId());
        dto1.setDeptName(department.getDeptName());
        dto1.setDeptNo(department.getDeptNo());
        dto1.setDeptSize(department.getDeptSize());
        return dto1;
    }
//Converting DepartmentDto to Department Entity
    private Department mapToEntity(DepartmentDto deptDto) {
        Department dept=new Department();
        dept.setDeptNo(deptDto.getDeptNo());
        dept.setDeptName(deptDto.getDeptName());
        dept.setDeptSize(deptDto.getDeptSize());
        return dept;
    }
}
