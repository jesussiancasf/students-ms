package com.scotiabank.ms.student.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConflictException extends RuntimeException {
    @Builder.Default
    private final int errorCode = HttpStatus.CONFLICT.value();
    @Builder.Default
    private final String errorName = HttpStatus.CONFLICT.name();
    private final String errorMsg;

}
