package com.davidsonperez.evalservice.evaluationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Exam;
import com.davidsonperez.evalservice.evaluationservice.data.entity.Option;
import com.davidsonperez.evalservice.evaluationservice.data.entity.Question;
import com.davidsonperez.evalservice.evaluationservice.data.repository.ExamRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.OptionRepository;
import com.davidsonperez.evalservice.evaluationservice.data.repository.QuestionRepository;
import com.davidsonperez.evalservice.evaluationservice.web.dto.ExamDto; 
import com.davidsonperez.evalservice.evaluationservice.web.mapper.ExamMapper;
import com.davidsonperez.evalservice.evaluationservice.web.mapper.OptionMapper;
import com.davidsonperez.evalservice.evaluationservice.web.mapper.QuestionMapper;

@Service
public class ExamService {
    private ExamRepository examRepository;
    private OptionRepository optionRepository;
    private QuestionRepository questionRepository;

    public ExamService(ExamRepository examRepository, OptionRepository optionRepository, 
    QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    public ExamDto saveExam(ExamDto examDto) throws Exception {
        if (examDto == null) {
            throw new Exception("Parámetro no válido");
        }

        examDto.setExamLink("http://localhost:4200/exam-presentation/take-exam");

        final Exam eExam =  examRepository.save(ExamMapper.INSTANCE.examDtoToExam(examDto));

        examDto.getQuestions().forEach(q -> {
            final Question question = QuestionMapper.INSTANCE.questionDtoToQuestion(q);
            question.setExam(eExam);
            questionRepository.save(question);

            if(q.getOptions() != null) {
                q.getOptions().forEach(o -> {
                    Option option = OptionMapper.INSTANCE.optionDtoToOption(o);
                    option.setQuestion(question);
                    optionRepository.save(option);
                });
            }
        });


        return getExam(eExam.getIdExam());
    }

    public ExamDto getExam(Long idExam) {
        Optional<Exam> exam;
        exam = examRepository.findById(idExam);

        ExamDto examDto = null;

        if (exam.isPresent()) {
            examDto = ExamMapper.INSTANCE.examToExamDto(exam.get());
        }

        return examDto;
    }

    public boolean deleteExam(Long idExam) {
        Boolean exists = examRepository.existsById(idExam);

        if (exists) {
            examRepository.deleteById(idExam);
        }
        else {
            return false;
        }

        return exists;
    }

    public List<ExamDto> getAllExams() {
        List<Exam> exams; 
        exams = examRepository.findAll();

        return ExamMapper.INSTANCE.examsToExamDtos(exams);
    }
}
