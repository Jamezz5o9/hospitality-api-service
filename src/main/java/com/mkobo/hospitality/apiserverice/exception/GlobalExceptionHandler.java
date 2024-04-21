package com.mkobo.hospitality.apiserverice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<CustomResponse.ErrorDetail> errorDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new CustomResponse.ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        CustomResponse<Object> responseBody = CustomResponse.builder()
                .errors(errorDetails)
                .build();

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<CustomResponse<String>> handleGenericException(GenericException ex) {
        CustomResponse<String> responseBody = CustomResponse.<String>builder()
                .errors(Collections.singletonList(new CustomResponse.ErrorDetail(null, ex.getMessage())))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleNotFoundException(NotFoundException ex) {
        CustomResponse<String> responseBody = CustomResponse.<String>builder()
                .errors(Collections.singletonList(new CustomResponse.ErrorDetail(null, ex.getMessage())))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<CustomResponse<String>> handleClassCastException(ClassCastException ex) {
        log.error("ClassCastException encountered: ", ex);
        CustomResponse<String> responseBody = CustomResponse.<String>builder()
                .errors(Collections.singletonList(new CustomResponse.ErrorDetail(null, "Class cast operation was not successful. Please ensure the data types are correct.")))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomResponse<String>> handleSQLException(SQLException ex) {
        CustomResponse<String> responseBody = CustomResponse.<String>builder()
                .errors(Collections.singletonList(new CustomResponse.ErrorDetail(null, ex.getMessage())))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
