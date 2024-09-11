package com.scotiabank.ms.student.service;

import com.scotiabank.ms.student.builder.StudentBuilder;
import com.scotiabank.ms.student.exception.BadRequestException;
import com.scotiabank.ms.student.exception.ConflictException;
import com.scotiabank.ms.student.mock.Mocks;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.repository.StudentRepository;
import com.scotiabank.ms.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {

    private static Student student;
    private static StudentRequest studentRequest;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void beforeEach() {
        student = Mocks.student();
        studentRequest = Mocks.studentRequest();
    }

    @Test
    void getAllStudentsByStatusActive() {
        Mockito.when(studentRepository.findStudentsByStatus(any())).thenReturn(Flux.just(student));
        StepVerifier.create(studentService.getAllStudentsByStatusActive())
                .assertNext(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    void saveStudent() {
        Mockito.when(studentRepository.findStudentByID(anyString())).thenReturn(Mono.just(student));
        Mockito.when(studentRepository.saveStudent(any())).thenReturn(Mono.just(student));

        StepVerifier.create(studentService.saveStudent(studentRequest))
                .verifyError(ConflictException.class);

        Mockito.when(studentRepository.findStudentByID(anyString())).thenReturn(Mono.empty());
        StepVerifier.create(studentService.saveStudent(studentRequest))
                .verifyComplete();
    }

    @Test
    void validStatusTest(){
        StudentRequest request = studentRequest;
        request.setEstado("another");

        Assertions.assertThrowsExactly(BadRequestException.class, () -> {
            StudentBuilder.studentRequestToEntity(request);
        });    }
}