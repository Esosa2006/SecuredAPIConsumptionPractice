package com.example.Practice_Security.services;

import com.example.Practice_Security.dtos.StudentDto;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<StudentDto> getProfile(String email);
}
