package com.davidsonperez.evalservice.evaluationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Answer;
import com.davidsonperez.evalservice.evaluationservice.data.entity.Exam;
import com.davidsonperez.evalservice.evaluationservice.data.entity.ExamPresentation;
import com.davidsonperez.evalservice.evaluationservice.data.entity.Option;
import com.davidsonperez.evalservice.evaluationservice.data.entity.Student;
import com.davidsonperez.evalservice.evaluationservice.data.repository.AnswerRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.ExamPresentationRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.ExamRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.OptionRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.StudentRepository;
import com.davidsonperez.evalservice.evaluationservice.web.dto.ExamPresentationDto;
import com.davidsonperez.evalservice.evaluationservice.web.mapper.AnswerMapper;
import com.davidsonperez.evalservice.evaluationservice.web.mapper.ExamPresentationMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExamPresentationService {
    private ExamPresentationRepository examPresentationRepository;
    private AnswerRepository answerRepository;
    private ExamRepository examRepository;
    private StudentRepository studentRepository;
    private OptionRepository optionRepository;
    
    public ExamPresentationService(ExamPresentationRepository examPresentationRepository, AnswerRepository answerRepository,
    ExamRepository examRepository, StudentRepository studentRepository, OptionRepository optionRepository) {
        this.examPresentationRepository = examPresentationRepository;
        this.answerRepository = answerRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.optionRepository = optionRepository;
    }

    public ExamPresentationDto takeExam(ExamPresentationDto examPresentationDto, Long idStudent, Long idExam) throws Exception{
        if (examPresentationDto == null) {
            throw new Exception("Parámetro no válido");
        }

        final ExamPresentation eExamPresentation = examPresentationRepository.save(ExamPresentationMapper
        .INSTANCE.examPresentationDtoToExamPresentation(examPresentationDto));

        Optional<Student> student = studentRepository.findById(idStudent);

        if (!student.isPresent()) {
            throw new Exception("El estudiante no existe");
        }

        eExamPresentation.setStudent(student.get());

        Optional<Exam> exam = examRepository.findById(idExam);

        if (!exam.isPresent()) {
            throw new Exception("El examen no existe");
        }

        eExamPresentation.setExam(exam.get());

        examPresentationDto.getAnswers().forEach(a -> {
            final Answer answer = AnswerMapper.INSTANCE.answerDtoToAnswer(a);
            answer.setExamPresentation(eExamPresentation);
            if (a.getOptionIds()  != null) {
                answer.setOptions(new ArrayList<>());
                a.getOptionIds().forEach(o -> {
                    Optional<Option> option = optionRepository.findById(o);
                    answer.getOptions().add(option.get());
                    if (option.get().getIsCorrect() == true) {
                        eExamPresentation.setScore(eExamPresentation.getScore() + option.get().getQuestion().getAssessment());
                    }
                });
            }

            answer.setExamPresentation(eExamPresentation);
            answerRepository.save(answer);
        });

        return getExamPresentation(eExamPresentation.getIdExamPresentation());
    }

    public ExamPresentationDto getExamPresentation(Long idExamPresentation) {
        Optional<ExamPresentation> examPresentation;
        examPresentation = examPresentationRepository.findById(idExamPresentation);

        ExamPresentationDto examPresentationDto = null;



        if (examPresentation.isPresent()) {
            log.info("Datos almecenados:{}", examPresentation.get());
            examPresentationDto = ExamPresentationMapper.INSTANCE.examPresentationToExamPresentationDto(examPresentation.get());
            examPresentationDto.getAnswers().forEach(a -> {
                List<Long> optionIds = examPresentation.get().getAnswers().stream()
                .filter(aDto -> Objects.equals(aDto.getIdAnswer(), a.getIdAnswer()))
                .flatMap(bDto -> bDto.getOptions().stream())
                .map(o -> o.getIdOption())
                .toList();
                a.setOptionIds(optionIds);
            });
        }

        return examPresentationDto;
    }

    public boolean deleteExamPresentation(Long idExamPresentation) {
        Boolean exists = examPresentationRepository.existsById(idExamPresentation);

        if (exists) {
            examPresentationRepository.deleteById(idExamPresentation);
        }
        else {
            return false;
        }

        return exists;
    }

    public List<ExamPresentationDto> getAllPresentations() {
        List<ExamPresentation> examPresentations; 
        examPresentations = examPresentationRepository.findAll();

        return ExamPresentationMapper.INSTANCE.presentationsTopresentationDtos(examPresentations);
    }
}
