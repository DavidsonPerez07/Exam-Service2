package com.davidsonperez.evalservice.evaluationservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
