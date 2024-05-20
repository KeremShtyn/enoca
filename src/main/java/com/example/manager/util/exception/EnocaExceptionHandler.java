package com.example.manager.util.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class EnocaExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus httpStatus, WebRequest webRequest) {
        log.error("message : {}  ex : {}", exception.getMessage(), exception);
        final String path = webRequest.getDescription(false);

        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        var signError = new EnocaError.SignErrorBuilder()
                .withTimestamp(LocalDateTime.now())
                .withStatus(httpStatus.value())
                .withMessage(httpStatus.name())
                .withPath(path)
                .withDetail(validationErrors.toString())
                .withType(exception.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(signError, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(WebRequest webRequest, Exception exception) {
        log.error("message : {}  ex : {}", exception.getMessage(), exception);

        var responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus httpStatus = StringUtils.isEmpty(responseStatus) ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = exception.getLocalizedMessage();
        final String path = webRequest.getDescription(false);
        final String message = StringUtils.isEmpty(localizedMessage) ? localizedMessage : httpStatus.getReasonPhrase();

        var signError = new EnocaError.SignErrorBuilder()
                .withTimestamp(LocalDateTime.now())
                .withStatus(httpStatus.value())
                .withMessage(message)
                .withDetail(exception.getCause() != null ? exception.getCause().getLocalizedMessage() : "")
                .withPath(path)
                .withType(exception.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(signError, httpStatus);
    }

    @ExceptionHandler(EnocaException.class)
    public ResponseEntity<Object> handleSignException(WebRequest webRequest, EnocaException enocaException) {
        log.error("message : {}  ex : {}", enocaException.getMessage(), enocaException);
        final String path = webRequest.getDescription(false);


        EnocaError enocaError = new EnocaError.SignErrorBuilder()
                .withTimestamp(LocalDateTime.now())
                .withStatus(enocaException.getErrorCode().getCode())
                .withMessage(enocaException.getMessage())
                .withDetail(enocaException.getErrorDetail())
                .withPath(path)
                .withType(enocaException.getClass().getSimpleName())
                .build();

        return new ResponseEntity<>(enocaError, enocaException.getErrorCode().getHttpStatus());
    }


}
