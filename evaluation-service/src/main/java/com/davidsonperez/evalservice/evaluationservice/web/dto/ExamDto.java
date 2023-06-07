package com.davidsonperez.evalservice.evaluationservice.web.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExamDto implements Serializable {
    private Long idExam;
    private String introduction;
    private Double maxScore;
    private String examLink;
    private List<QuestionDto> questions;
}
