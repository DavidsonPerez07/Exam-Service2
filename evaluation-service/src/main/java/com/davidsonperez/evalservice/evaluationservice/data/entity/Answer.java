package com.davidsonperez.evalservice.evaluationservice.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "answer")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnswer;
    private String openAnswer;
    @Column(nullable = false)
    private Double assessment;

    @ManyToMany
    @JoinTable(name = "answeroption", 
        joinColumns = @JoinColumn(name = "id_answer"), 
        inverseJoinColumns = @JoinColumn(name = "id_option"))
    private List<Option> options;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_exam_presentation")
    private ExamPresentation examPresentation;
}
