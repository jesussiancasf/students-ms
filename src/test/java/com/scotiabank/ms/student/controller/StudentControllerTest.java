package com.scotiabank.ms.student.controller;

import com.scotiabank.ms.student.mock.Mocks;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "20000")
class StudentControllerTest {

    private static final String URL_BASE = "/api/student";
    private static final String URL_METHOD = URL_BASE + "/";
    private static Student student;
    private static StudentRequest studentRequest;

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private StudentService studentService;

    @BeforeEach
    void beforeEach() {
        student = Mocks.student();
        studentRequest = Mocks.studentRequest();
    }

    @Test
    void getTest() {

        Mockito.when(studentService.getAllStudentsByStatusActive()).thenReturn(Flux.just(student));

        webClient.get()
                .uri(URL_METHOD)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void postTest() {
        Mockito.when(studentService.saveStudent(any())).thenReturn(Mono.empty());
        webClient.post()
                .uri(URL_METHOD)
                .body(Mono.just(studentRequest), StudentRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void postTestError() {
        StudentRequest studentReq = studentRequest;
        studentReq.setNombre(null);

        Mockito.when(studentService.saveStudent(any())).thenReturn(Mono.empty());
        webClient.post()
                .uri(URL_METHOD)
                .body(Mono.just(studentReq), StudentRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();
    }

}