package br.com.phricardo.idJava.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserLoginRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull @NotEmpty
    private String password;
}
