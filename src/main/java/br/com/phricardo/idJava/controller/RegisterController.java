package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.dto.ErrorResponseDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
import br.com.phricardo.idJava.service.RegisterService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody @NotNull UserRegisterRequestDto userRegisterRequestDto) {
         return registerService.register(userRegisterRequestDto);
    }

}
