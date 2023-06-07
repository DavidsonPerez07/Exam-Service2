package com.davidsonperez.evalservice.evaluationservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}
