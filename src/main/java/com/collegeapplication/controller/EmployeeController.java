package com.collegeapplication.controller;
import com.collegeapplication.payload.EmployeeDto;
import com.collegeapplication.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("departments/{id}/employees")
    public ResponseEntity<Object> createOneEmployee(@PathVariable("id")long id,
                                                         @Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
    return new ResponseEntity<>(employeeService.createOneEmployeeDto(id, employeeDto), HttpStatus.CREATED);
    }
    @GetMapping("departments/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return new  ResponseEntity<>(allEmployees,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("departments/{did}/employees/{eid}")
    public ResponseEntity<EmployeeDto> updateOneEmployee(
            @PathVariable("did")long did,@PathVariable("eid")long eid,@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto employeeDto1 = employeeService.updateOneEmployee(did, eid, employeeDto);
        return new ResponseEntity<>(employeeDto1,HttpStatus.OK);
    }
    //GetEmployee by EmployeeId
    @GetMapping("departments/{did}/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmployeeId(
            @PathVariable("did")long did,@PathVariable("id") long eid){
        EmployeeDto empDto = employeeService.getOneEmployeeByEmployeeId(did, eid);
        return new ResponseEntity<>(empDto,HttpStatus.OK);
    }
    //Deleting Employee By using DeptId & EmployeeId
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/departments/{did}/employees/{eid}")
    public ResponseEntity<String> deleteOneEmployee(@PathVariable("did")long did,@PathVariable("eid")long eid){
        return new ResponseEntity<>("Deleted Employee Successfully!"+eid,HttpStatus.OK);
    }
   // Get All employee based on Department id
    @GetMapping("/departments/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByDepartmentId(@PathVariable("id")long id){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployeeByDepartmentId(id);
        return new ResponseEntity<List<EmployeeDto>>(employeeDto, HttpStatus.OK);
    }
    //GetEmployeeByEmail
    @GetMapping("/departments/{id}/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable("id")long id,@PathVariable("email")String email){
        EmployeeDto empDto = employeeService.getOneEmployeeByEmail(id, email);
        return new ResponseEntity<>(empDto,HttpStatus.OK);
    }
}
