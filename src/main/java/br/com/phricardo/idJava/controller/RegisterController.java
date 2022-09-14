package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.ErrorResponseDto;
import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.service.RegisterService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto, Errors errors) {
       if (errors.hasErrors()) {
         List<String> listErrors = new ArrayList<>();
         for (final FieldError error : errors.getFieldErrors()) {
           listErrors.add(error.getField() + ": " + error.getDefaultMessage());
         }

         return new ResponseEntity<>(
             ErrorResponseDto.builder()
             .numErros(errors.getErrorCount())
             .errors(listErrors)
             .build(),
             HttpStatus.BAD_REQUEST
         );
       }

       return registerService.register(userRegisterRequestDto);
    }

}
