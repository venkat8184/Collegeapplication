package com.collegeapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Department name should have at least 2 characters")
    private String deptName;
    @NotEmpty
    @Size(min = 2, message = "Department number should have at least 2 characters")
    private String deptNo;
    private int deptSize;
}
