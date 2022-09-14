package br.com.phricardo.idJava.exception;

import br.com.phricardo.idJava.dto.ErrorResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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
//  ServerErrorMessage serverErrorMessage = psqlException.getServerErrorMessage();
    List<String> errors = new ArrayList<>();
    errors.add(psqlException.getLocalizedMessage());
    return new ResponseEntity<>(new ErrorResponseDto(1, errors.toString()), HttpStatus.CONFLICT);
  }

}
