package com.scotiabank.ms.student.repository;

import com.scotiabank.ms.student.model.entity.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentCrudRepository extends ReactiveCrudRepository<Student, String> {
    @Query("SELECT * FROM estudiantes WHERE estado = :status")
    Flux<Student> findByStatus(String status);

    @Query("INSERT INTO estudiantes (id, nombre, apellido, estado, edad) VALUES (:id, :name, :lastname, :status, :age)")
    Mono<Student> saveStudent(String id, String name, String lastname, String status, int age);
}
