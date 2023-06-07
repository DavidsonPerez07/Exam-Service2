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
public class AnswerDto implements Serializable {
    private Long idAnswer;
    private String openAnswer;
    private Double assessment;
    private List<Long> optionIds;
}
