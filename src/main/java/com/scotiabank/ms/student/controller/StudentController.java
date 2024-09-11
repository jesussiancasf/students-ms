package com.scotiabank.ms.student.controller;

import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.service.StudentService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public Flux<Student> studentsStatusTrue() {
        return studentService.getAllStudentsByStatusActive();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveStudent(@RequestBody @Valid StudentRequest student) {
        return studentService.saveStudent(student);
    }

}
