package com.example.Practice_Security.controllers;

import com.example.Practice_Security.dtos.LoginDto;
import com.example.Practice_Security.dtos.StudentRegistrationDto;
import com.example.Practice_Security.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> signUp(@Valid @RequestBody StudentRegistrationDto studentRegistrationDto){
        return authService.signUp(studentRegistrationDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }
}
