package com.scotiabank.ms.student.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentRequest {
    @NotEmpty(message = "No puede estar vacío.")
    @NotNull(message = "No puede ser nulo.")
    private String id;
    @NotEmpty(message = "No puede estar vacío.")
    @NotNull(message = "No puede ser nulo.")
    private String nombre;
    @NotEmpty(message = "No puede estar vacío.")
    @NotNull(message = "No puede ser nulo.")
    private String apellido;
    @NotEmpty(message = "No puede estar vacío.")
    @NotNull(message = "No puede ser nulo.")
    private String estado;
    @NotNull(message = "No puede ser nulo.")
    private int edad;

}
