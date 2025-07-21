package com.example.Practice_Security.mappers;

import com.example.Practice_Security.dtos.StudentDto;
import com.example.Practice_Security.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDto toDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDepartment(student.getDepartment());
        return studentDto;
    }
}
