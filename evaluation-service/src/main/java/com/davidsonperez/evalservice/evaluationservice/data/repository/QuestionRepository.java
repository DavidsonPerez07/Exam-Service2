package com.davidsonperez.evalservice.evaluationservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
