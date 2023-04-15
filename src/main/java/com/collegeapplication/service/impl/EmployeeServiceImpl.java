package com.collegeapplication.service.impl;

import com.collegeapplication.entities.Department;
import com.collegeapplication.entities.Employee;
import com.collegeapplication.exception.ResourceNotFoundException;
import com.collegeapplication.exception.ResourceNotFoundWithEmailException;
import com.collegeapplication.payload.EmployeeDto;
import com.collegeapplication.repositories.DepartmentRepository;
import com.collegeapplication.repositories.EmployeeRepository;
import com.collegeapplication.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

   @Autowired
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createOneEmployeeDto(long departmentId,EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department", "deptId", departmentId));
        Employee employee=new Employee();
        Employee emp1 = modelMapper.map(employeeDto, Employee.class);
        emp1.setDepartment(department);
        Employee save = employeeRepository.save(emp1);
        return modelMapper.map(save, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto updateOneEmployee(long did, long eid, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(did).orElseThrow(() -> new ResourceNotFoundException("Department", "deptId", did));
        Employee employee = employeeRepository.findById(eid).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", eid));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobile(employeeDto.getMobile());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(department);
        Employee employee1 = employeeRepository.save(employee);
return modelMapper.map(employee1,EmployeeDto.class);
    }

    @Override
    public EmployeeDto getOneEmployeeByEmployeeId(long did, long eid) {
        Department department = departmentRepository.findById(did).orElseThrow(() ->
                new ResourceNotFoundException("Department", "deptId", did));

        Employee employee = employeeRepository.findById(eid).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "EmployeeId", eid));
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public void deleteOneEmployee(long did, long eid) {
        Department department = departmentRepository.findById(did).orElseThrow(
                () -> new ResourceNotFoundException("Department", "DeptId", did));
        Employee employee = employeeRepository.findById(eid).orElseThrow(() -> new ResourceNotFoundException("Employee", "EmpId", eid));
    employeeRepository.deleteById(eid);
    }

    @Override
    public List<EmployeeDto> getAllEmployeeByDepartmentId(long id) {
        List<Employee> employees = employeeRepository.findEmployeeByDepartmentId(id);
        List<EmployeeDto> employeedtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeedtos;
    }

    @Override
    public EmployeeDto getOneEmployeeByEmail(long deptId, String email) {
        Department department = departmentRepository.findById(deptId).orElseThrow(() ->
                new ResourceNotFoundException("Department", "deptId", deptId));

        Employee employee = employeeRepository.findOneEmployeeByEmail(email);
        if (employee == null) {
                throw new ResourceNotFoundWithEmailException(email);
            }
            return modelMapper.map(employee,EmployeeDto.class);
    }


}
