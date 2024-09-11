package com.scotiabank.ms.student.service;

import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.model.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> getAllStudentsByStatusActive();
    Mono<Void> saveStudent(StudentRequest student);
}
