package com.scotiabank.ms.student.repository;

import com.scotiabank.ms.student.mock.Mocks;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.repository.impl.StudentRepositoryImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
class StudentRepositoryImplTest {

    private static Student student;

    @Mock
    private StudentCrudRepository crudRepository;

    @InjectMocks
    private StudentRepositoryImpl studentRepository;

    @BeforeEach
    void beforeEach() {
        student = Mocks.student();
    }

    @Test
    void saveStudent() {
        Mockito.when(crudRepository.saveStudent(anyString(), anyString(), anyString(), anyString(), anyInt()))
                .thenReturn(Mono.just(student));
        StepVerifier.create(studentRepository.saveStudent(student))
                .assertNext(student1 -> {
                    Assertions.assertEquals(student1.getAge(), 10);
                    Assertions.assertNotNull(student1.getId());
                })
                .verifyComplete();
    }

    @Test
    void findStudentByID() {
        Mockito.when(crudRepository.findById(anyString())).thenReturn(Mono.just(student));
        StepVerifier.create(studentRepository.findStudentByID("id"))
                .assertNext(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    void findStudentsByStatus() {
        Mockito.when(crudRepository.findByStatus(anyString())).thenReturn(Flux.just(student));
        StepVerifier.create(studentRepository.findStudentsByStatus("status"))
                .assertNext(Assertions::assertNotNull)
                .verifyComplete();
    }
}