package br.com.phricardo.idJava.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginAuthRequestDto {
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull @Length(min = 5, max = 10)
    private String password;
}