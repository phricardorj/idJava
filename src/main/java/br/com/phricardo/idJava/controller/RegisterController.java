package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.dto.ErrorResponseDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
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

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<ErrorResponseDto> register(@Valid @RequestBody @NotNull UserRegisterRequestDto userRegisterRequestDto) {
        UserModel userModel = UserModel.builder()
                .userId(UUID.randomUUID())
                .username(userRegisterRequestDto.getUsername())
                .fullName(userRegisterRequestDto.getFullName())
                .cpfCnpj(userRegisterRequestDto.getCpfCnpj())
                .email(userRegisterRequestDto.getEmail())
                .password(encoder.encode(userRegisterRequestDto.getPassword()))
                .active(true)
                .build();

        userRepository.save(userModel);

        return new ResponseEntity<>(new ErrorResponseDto("User created successfully"), HttpStatus.CREATED);
    }

}
