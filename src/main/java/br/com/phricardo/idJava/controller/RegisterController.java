package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.model.UserModel;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping()
    public ResponseEntity<UserModel> register(@Valid @RequestBody @NotNull UserRegisterRequestDto userRegisterRequestDto){
      UserModel userModel = UserModel.builder()
              .id(UUID.randomUUID())
              .username(userRegisterRequestDto.getUsername())
              .fullName(userRegisterRequestDto.getFullName())
              .cpfCnpj(userRegisterRequestDto.getCpfCnpj())
              .email(userRegisterRequestDto.getEmail())
              .password(userRegisterRequestDto.getPassword())
              .active(true)
              .build();

        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }

}
