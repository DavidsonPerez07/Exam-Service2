package com.davidsonperez.evalservice.evaluationservice.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Exam;
import com.davidsonperez.evalservice.evaluationservice.web.dto.ExamDto;

@Mapper
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    Exam examDtoToExam(ExamDto examDto);
    
    ExamDto examToExamDto(Exam exam);
    
    List<ExamDto> examsToExamDtos(List<Exam> exams);
}
