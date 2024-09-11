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
public class BadRequestException extends RuntimeException {
    @Builder.Default
    private final int errorCode = HttpStatus.BAD_REQUEST.value();
    @Builder.Default
    private final String errorName = HttpStatus.BAD_REQUEST.name();
    private final String errorMsg;
}
