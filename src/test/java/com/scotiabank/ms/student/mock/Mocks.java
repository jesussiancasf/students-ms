package com.scotiabank.ms.student.mock;

import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.model.request.StudentRequest;

public final class Mocks {

    private Mocks() {
    }

    public static Student student(){
        return Student.builder()
                .id("id")
                .name("name")
                .status("activo")
                .age(10)
                .lastName("lastname")
                .build();
    }

    public static StudentRequest studentRequest(){
        return StudentRequest.builder()
                .id("id")
                .nombre("name")
                .estado("activo")
                .edad(10)
                .apellido("lastname")
                .build();
    }
}
