package com.mcamardella.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude
public class CustomResponse {
    protected LocalDateTime localDateTime;
    protected int statusCode;
    protected HttpStatus httpStatus;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}
