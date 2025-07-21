package com.example.Practice_Security.controllers;

import com.example.Practice_Security.dtos.StudentDto;
import com.example.Practice_Security.services.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/profile")
    public ResponseEntity<StudentDto> getProfile(Authentication authentication){
        String email = authentication.getName();
        return studentService.getProfile(email);
    }
}
