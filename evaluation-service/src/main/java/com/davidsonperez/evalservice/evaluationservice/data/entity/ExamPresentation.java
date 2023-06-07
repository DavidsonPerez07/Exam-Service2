package com.davidsonperez.evalservice.evaluationservice.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "exampresentation")
public class ExamPresentation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamPresentation;
    @Column(nullable = false)
    private Double score;
    
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;

    @OneToMany(mappedBy = "examPresentation")
    private List<Answer> answers;
}
