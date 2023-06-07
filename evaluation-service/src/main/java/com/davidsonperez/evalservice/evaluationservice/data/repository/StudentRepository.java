package com.davidsonperez.evalservice.evaluationservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
