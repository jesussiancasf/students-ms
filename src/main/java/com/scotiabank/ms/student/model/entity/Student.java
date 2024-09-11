package com.scotiabank.ms.student.model.entity;

import com.scotiabank.ms.student.exception.BadRequestException;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("estudiantes")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Student {

    @Id
    private String id;
    @Column("nombre")
    private String name;
    @Column("apellido")
    private String lastName;
    @Column("estado")
    private String status;
    @Column("edad")
    private int age;
}
