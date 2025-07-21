package com.example.Practice_Security.security;

import com.example.Practice_Security.entities.Student;
import com.example.Practice_Security.exceptions.StudentNotFoundException;
import com.example.Practice_Security.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;

    @Autowired
    public MyUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByemail(email);
        if (student != null){
            return new UserPrincipal(student.getEmail(), student.getPassword());
        }
        throw new StudentNotFoundException("Student Not Found!");
    }
}
