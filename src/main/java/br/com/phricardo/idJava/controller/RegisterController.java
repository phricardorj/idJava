package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
      log.info("Register Request");
       return registerService.register(userRegisterRequestDto);
    }

}
