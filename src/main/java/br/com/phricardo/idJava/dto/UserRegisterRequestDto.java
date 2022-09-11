package br.com.phricardo.idJava.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class UserRegisterRequestDto {
    @NotNull @NotEmpty @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "invalid username")
    private String username;
    @NotNull @NotEmpty @Pattern(regexp = "^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+(?:$|\\s+)){2,}$", message = "invalid full name")
    private String fullName;
    @NotNull @NotEmpty @Pattern(regexp = "^(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}|\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "invalid CPF or CNPJ")
    private String cpfCnpj;
    @NotNull @NotEmpty @Email(message = "invalid email")
    private String email;
    @NotNull @NotEmpty @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$", message = "invalid password")
    private String password;
}
