package br.com.phricardo.idJava.Exception;


import br.com.phricardo.idJava.Dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) {
        log.info("MethodArgumentNotValidException Handler");

        List<String> listErrors = new ArrayList<>();
        for (final FieldError error : exception.getFieldErrors()) {
            listErrors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .numErros(exception.getErrorCount())
                        .errors(listErrors.toString())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<?> psqlExceptionHandler(PSQLException psqlException) {
        log.info("PSQLException Handler");
        ServerErrorMessage serverErrorMessage = psqlException.getServerErrorMessage();
        assert serverErrorMessage != null;
        String[] errorArr = Objects.requireNonNull(serverErrorMessage.getDetail()).split("=");
        String key = errorArr[0].replace("(", "").replace(")", "").replace("Key ", "");
        String error = key + " " + errorArr[1].replace(".", "");

        return new ResponseEntity<>(new ErrorResponseDto(1, error), HttpStatus.CONFLICT);
    }

}