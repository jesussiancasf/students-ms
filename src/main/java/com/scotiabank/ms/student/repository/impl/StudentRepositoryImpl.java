package com.scotiabank.ms.student.repository.impl;

import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.repository.StudentCrudRepository;
import com.scotiabank.ms.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final StudentCrudRepository studentCrudRepository;

    @Override
    public Mono<Student> saveStudent(Student student) {
        return studentCrudRepository.saveStudent(
                        student.getId(),
                        student.getName(),
                        student.getLastName(),
                        student.getStatus(),
                        student.getAge())
                .retry(2);
    }

    @Override
    public Mono<Student> findStudentByID(String id) {
        return studentCrudRepository.findById(id)
                .retry(2);
    }

    @Override
    public Flux<Student> findStudentsByStatus(String status) {
        return studentCrudRepository.findByStatus(status)
                .retry(2);
    }
}
