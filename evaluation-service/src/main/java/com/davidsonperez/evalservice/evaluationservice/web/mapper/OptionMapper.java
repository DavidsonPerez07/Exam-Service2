package com.davidsonperez.evalservice.evaluationservice.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Option;
import com.davidsonperez.evalservice.evaluationservice.web.dto.OptionDto;

@Mapper
public interface OptionMapper {
    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    Option optionDtoToOption(OptionDto optionDto);

    OptionDto optionToOptionDto(Option option);

    List<Option> optionDtosToOptions(List<OptionDto> optionDto);

    List<OptionDto> optionsToOptionDtos(List<Option> option);
    
}
