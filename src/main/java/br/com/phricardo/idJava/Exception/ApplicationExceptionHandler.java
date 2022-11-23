package br.com.phricardo.idJava.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        log.info("MethodArgumentNotValidException Handler");

        List<String> listErrors = new ArrayList<>();
        for (final FieldError error : exception.getFieldErrors()) {
            listErrors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return new ResponseEntity<>(listErrors, HttpStatus.BAD_REQUEST);
    }
}