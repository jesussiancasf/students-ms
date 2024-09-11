package com.scotiabank.ms.student.repository;

import com.scotiabank.ms.student.model.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository {
    Mono<Student> saveStudent(Student student);

    Mono<Student> findStudentByID(String id);

    Flux<Student> findStudentsByStatus(String status);
}
