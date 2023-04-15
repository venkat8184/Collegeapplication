package com.collegeapplication.payload;

import lombok.Data;

@Data
public class LogInDto {
    private String userNameOrEmail;
    private String password;
}
