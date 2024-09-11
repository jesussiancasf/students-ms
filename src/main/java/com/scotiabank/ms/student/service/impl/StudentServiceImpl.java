package com.scotiabank.ms.student.service.impl;

import com.scotiabank.ms.student.builder.StudentBuilder;
import com.scotiabank.ms.student.exception.ConflictException;
import com.scotiabank.ms.student.model.request.StudentRequest;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.repository.StudentRepository;
import com.scotiabank.ms.student.service.StudentService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final String ACTIVE = "activo";
    private final StudentRepository studentRepository;

    @Override
    public Flux<Student> getAllStudentsByStatusActive() {
        return studentRepository.findStudentsByStatus(ACTIVE);
    }

    @Override
    public Mono<Void> saveStudent(@Valid StudentRequest requestStudent) {

        return getStudentByID(requestStudent.getId())
                .switchIfEmpty(studentRepository.saveStudent(StudentBuilder.studentRequestToEntity(requestStudent)))
                .then();
    }

    private Mono<Student> getStudentByID(String id) {
        return studentRepository.findStudentByID(id)
                .flatMap((Student student) -> Mono.error(ConflictException.builder()
                        .errorMsg(String.format("Id %s already exists.", id))
                        .build()));
    }
}
