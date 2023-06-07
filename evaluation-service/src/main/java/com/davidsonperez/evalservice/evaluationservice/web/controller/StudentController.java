package com.davidsonperez.evalservice.evaluationservice.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.davidsonperez.evalservice.evaluationservice.service.StudentService;
import com.davidsonperez.evalservice.evaluationservice.web.dto.StudentDto;

@RestController
@RequestMapping("student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<?> insertStudent(@RequestBody StudentDto studentDto) throws Exception {
        if (studentDto == null) {
            return ResponseEntity.badRequest().body("Datos del estudiante inválidos");
        }

        StudentDto resp;
        try {
            resp = studentService.saveStudent(studentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/getStudent")
    public ResponseEntity<?> showStudent(@RequestParam Long idStudent) {
        StudentDto resp;
        resp = studentService.getStudent(idStudent);

        if(resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este estudiante no existe");
        }

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<?> showAllStudents() {
        List<StudentDto> studentDtos = studentService.getAllStudents();

        return ResponseEntity.ok(studentDtos);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<?> deleteStudent(@RequestParam Long idStudent) {
        Boolean resp;

        resp = studentService.deleteStudent(idStudent);

        if (resp == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este estudiante no existe");
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/editStudent")
    public ResponseEntity<?> editStudent(@RequestParam Long idStudent, @RequestBody StudentDto updateStudent) {
        boolean resp;

        resp = studentService.editStudent(idStudent, updateStudent);

        if (resp == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este estudiante no existe");
        }

        return ResponseEntity.ok().build();
    }
}
