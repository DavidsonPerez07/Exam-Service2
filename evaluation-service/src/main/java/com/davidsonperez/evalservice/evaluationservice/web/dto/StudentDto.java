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
public class StudentDto implements Serializable {
    private Long idStudent;
    private String idCard;
    private String name;
    private String email;
    
}
