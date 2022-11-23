package br.com.phricardo.idJava.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginAuthRequestDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}