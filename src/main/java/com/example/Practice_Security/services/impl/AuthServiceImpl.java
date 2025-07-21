package com.example.Practice_Security.services.impl;

import com.example.Practice_Security.dtos.LoginDto;
import com.example.Practice_Security.dtos.StudentRegistrationDto;
import com.example.Practice_Security.entities.Student;
import com.example.Practice_Security.exceptions.StudentAlreadyExistsException;
import com.example.Practice_Security.repos.StudentRepository;
import com.example.Practice_Security.security.JWTService;
import com.example.Practice_Security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final StudentRepository studentRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AuthServiceImpl(StudentRepository studentRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.studentRepository = studentRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<String> signUp(StudentRegistrationDto studentRegistrationDto) {
        Student student = studentRepository.findByemail(studentRegistrationDto.getEmail());
        if (student != null){
            throw new StudentAlreadyExistsException("Student already exists");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Student newStudent = new Student();
        newStudent.setName(studentRegistrationDto.getName());
        newStudent.setEmail(studentRegistrationDto.getEmail());
        newStudent.setDepartment(studentRegistrationDto.getDepartment());
        newStudent.setPassword(encoder.encode(studentRegistrationDto.getPassword()));
        studentRepository.save(newStudent);
        return ResponseEntity.ok("Registration Successful");
    }

    @Override
    public ResponseEntity<Object> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (!authentication.isAuthenticated()){
            throw new RuntimeException("Authentication failed!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(loginDto.getEmail()));
    }


}
