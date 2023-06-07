package com.davidsonperez.evalservice.evaluationservice.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "exam")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;
    @Column(nullable = false)
    private String introduction;
    @Column(nullable = false)
    private Double maxScore;
    @Column(nullable = false)
    private String examLink;
    @Column(name = "question")
    @OneToMany(mappedBy = "exam")
    private List<Question> questions;
}
