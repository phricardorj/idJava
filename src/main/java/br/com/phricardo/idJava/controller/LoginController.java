package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserLoginRequestDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
import br.com.phricardo.idJava.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class LoginController {

   private final LoginService loginService;

    @PostMapping()
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) throws Exception {
        return loginService.isValid(userLoginRequestDto);
    }

}
