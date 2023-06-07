package com.davidsonperez.evalservice.evaluationservice.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OptionDto implements Serializable {
    private Long idOption;
    private String description;
    private Boolean isCorrect;
}
