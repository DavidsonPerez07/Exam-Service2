/*ackage com.davidsonperez.evalservice.evaluationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.davidsonperez.evalservice.evaluationservice.data.entity.Student;
import com.davidsonperez.evalservice.evaluationservice.data.repository.StudentRepository;
import com.davidsonperez.evalservice.evaluationservice.web.dto.StudentDto;
import com.davidsonperez.evalservice.evaluationservice.web.mapper.StudentMapper;

public class StudentServiceTest {
    private StudentRepository studentRepository;
    private StudentService studentService;
    @Autowired
    private StudentDto studentDto;
    
    @BeforeEach
    public void setUp() {
        studentRepository = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
        Student studentMock = new Student();
        studentMock.setIdStudent(1L);
        studentMock.setIdCard("12345");
        studentMock.setName("Nelson");
        studentMock.setEmail("nelson123@gmail.com");    
        studentDto = StudentMapper.INSTANCE.studentToStudentDto(studentMock);
        Mockito.when(studentService.findOne(1L)).thenReturn(studentDto);
    }

    @Test
    public void testAllDataOk_ResultOk() throws Exception {
        Student resulted = new Student(1L, "12345", "Nelson", "nelson123@gmail.com");
        when(studentRepository.save(any(Student.class))).thenReturn(resulted);
        
        StudentDto student = new StudentDto(null, "12345", "NELSON", "nelson123@gmail.com");

        StudentService instance = new StudentService(studentRepository);

        StudentDto expResult = new StudentDto(1L, "12345", "Nelson", "nelson123@gmail.com");

        StudentDto result = instance.saveStudent(student);

        assertEquals(expResult.getIdStudent(), result.getIdStudent());
        assertEquals(expResult.getName(), result.getName());
    }
}*/
