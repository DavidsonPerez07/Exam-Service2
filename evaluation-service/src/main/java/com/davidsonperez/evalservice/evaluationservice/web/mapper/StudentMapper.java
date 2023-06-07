package com.davidsonperez.evalservice.evaluationservice.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Student;
import com.davidsonperez.evalservice.evaluationservice.web.dto.StudentDto;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student studentDtoToStudent(StudentDto studentDto);
    
    StudentDto studentToStudentDto(Student student);

    List<StudentDto> studentsToStudentDtos(List<Student> students);
}
