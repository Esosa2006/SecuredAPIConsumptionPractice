package com.example.Practice_Security.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentRegistrationDto {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Department is required!")
    private String department;
    @NotBlank(message = "Password is required!")
    private String password;
}
