package br.com.phricardo.idJava.exception;

import br.com.phricardo.idJava.dto.ErrorResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(PSQLException.class)
   public ResponseEntity<?> handleException2(PSQLException psqlException) {
     log.info("PSQLException Handler");
     ServerErrorMessage serverErrorMessage = psqlException.getServerErrorMessage();
     List<String> errors = new ArrayList<>();
     errors.add(psqlException.getLocalizedMessage());
     return new ResponseEntity<>(new ErrorResponseDto(1, errors), HttpStatus.CONFLICT);
   }
}
