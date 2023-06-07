package com.davidsonperez.evalservice.evaluationservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonperez.evalservice.evaluationservice.data.entity.ExamPresentation;

public interface ExamPresentationRepository extends JpaRepository<ExamPresentation, Long> {
    
}