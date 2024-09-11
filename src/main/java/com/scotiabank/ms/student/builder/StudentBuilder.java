package com.scotiabank.ms.student.builder;

import com.scotiabank.ms.student.exception.BadRequestException;
import com.scotiabank.ms.student.model.entity.Student;
import com.scotiabank.ms.student.model.request.StudentRequest;
import java.util.List;
import javax.validation.Valid;

public final class StudentBuilder {

    private StudentBuilder() {
    }

    public static Student studentRequestToEntity(@Valid StudentRequest studentRequest) {
        return Student.builder()
                .id(studentRequest.getId())
                .name(studentRequest.getNombre())
                .lastName(studentRequest.getApellido())
                .status(validStatus(studentRequest.getEstado()))
                .age(studentRequest.getEdad())
                .build();
    }

    private static String validStatus(String status) {
        if (!List.of("activo", "inactivo").contains(status)) {
            throw BadRequestException.builder()
                    .errorMsg(String.format("Invalid value '%s'", status))
                    .build();
        }
        return status;
    }

}
