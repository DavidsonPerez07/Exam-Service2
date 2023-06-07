package com.davidsonperez.evalservice.evaluationservice.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.davidsonperez.evalservice.evaluationservice.data.entity.ExamPresentation;
import com.davidsonperez.evalservice.evaluationservice.web.dto.ExamPresentationDto;

@Mapper
public interface ExamPresentationMapper {
    ExamPresentationMapper INSTANCE = Mappers.getMapper(ExamPresentationMapper.class);

    ExamPresentation examPresentationDtoToExamPresentation(ExamPresentationDto examPresentationDto);

    ExamPresentationDto examPresentationToExamPresentationDto(ExamPresentation examPresentation);
    
    List<ExamPresentationDto> presentationsTopresentationDtos(List<ExamPresentation> examPresentations);
}
