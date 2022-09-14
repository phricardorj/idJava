package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserLoginRequestDto;
import br.com.phricardo.idJava.service.LoginService;
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
@RequestMapping(value = "/login")
public class LoginController {

   private final LoginService loginService;

    @PostMapping()
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
      log.info("Login Request");
      return loginService.isValid(userLoginRequestDto);
    }

}
