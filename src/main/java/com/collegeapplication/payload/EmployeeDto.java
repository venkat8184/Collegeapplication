package com.collegeapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    @NotNull(message = "Id cannot be null")
    private long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Mobile cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Mobile should be a 10 digit number")
    private String mobile;

    @NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary cannot be negative")
    private int salary;

    // getters and setters
}

