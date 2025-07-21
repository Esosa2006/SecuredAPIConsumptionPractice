package com.example.Practice_Security.services;

import com.example.Practice_Security.dtos.LoginDto;
import com.example.Practice_Security.dtos.StudentRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<String> signUp(@Valid StudentRegistrationDto studentRegistrationDto);

    ResponseEntity<Object> login(@Valid LoginDto loginDto);
}
