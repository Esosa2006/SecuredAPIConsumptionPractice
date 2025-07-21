package com.example.Practice_Security.repos;

import com.example.Practice_Security.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByemail(String email);
}
