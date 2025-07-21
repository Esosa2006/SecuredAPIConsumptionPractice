package com.example.Practice_Security.services.impl;

import com.example.Practice_Security.dtos.StudentDto;
import com.example.Practice_Security.entities.Student;
import com.example.Practice_Security.exceptions.StudentNotFoundException;
import com.example.Practice_Security.mappers.StudentMapper;
import com.example.Practice_Security.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements com.example.Practice_Security.services.StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public ResponseEntity<StudentDto> getProfile(String email) {
        Student student = studentRepository.findByemail(email);
        if (student == null){
            throw new StudentNotFoundException("Student not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper.toDto(student));
    }
}
