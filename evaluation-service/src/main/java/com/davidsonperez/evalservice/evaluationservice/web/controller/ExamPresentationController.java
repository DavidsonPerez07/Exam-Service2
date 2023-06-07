package com.davidsonperez.evalservice.evaluationservice.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidsonperez.evalservice.evaluationservice.service.ExamPresentationService;
import com.davidsonperez.evalservice.evaluationservice.web.dto.ExamPresentationDto;

@RestController
@RequestMapping("exampresentation")
@CrossOrigin(origins = "http://localhost:4200")
public class ExamPresentationController {
    private ExamPresentationService examPresentationService;

    public ExamPresentationController(ExamPresentationService examPresentationService) {
        this.examPresentationService = examPresentationService;
    }

    @PostMapping("/takeExam")
    public ResponseEntity<?> takeExam(@RequestBody ExamPresentationDto examPresentationDto, @RequestParam Long idExam) throws Exception {
        if (examPresentationDto == null) {
            return ResponseEntity.badRequest().body("Datos de la presentación del examen inválidos");
        }

        ExamPresentationDto resp;
        try {
            resp = examPresentationService.takeExam(examPresentationDto, idExam, examPresentationDto.getStudentId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/getExamPresentation")
    public ResponseEntity<?> showExamPresentation(@RequestParam Long idExamPresentation) {
        ExamPresentationDto resp;
        resp = examPresentationService.getExamPresentation(idExamPresentation);

        if (resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta presentación de examen no existe");
        }

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/deleteExamPresentation")
    public ResponseEntity<?> deleteExamPresentation(@RequestParam Long idExamPresentation) {
        Boolean resp;

        resp = examPresentationService.deleteExamPresentation(idExamPresentation);

        if (resp == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esta presentación de examen no existe");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllPresentations")
    public ResponseEntity<?> showAllExams() {
        List<ExamPresentationDto> examPresentationDtos = examPresentationService.getAllPresentations();

        return ResponseEntity.ok(examPresentationDtos);
    }
}
