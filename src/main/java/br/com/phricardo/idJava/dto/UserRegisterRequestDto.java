package br.com.phricardo.idJava.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterRequestDto  {
    @NotEmpty @Pattern(message = "Password does not respect pattern", regexp = "^[\\w](?!.*?\\.{2})[\\w.]{1,28}[\\w]$")
    private String username;
    @NotBlank @Pattern(message = "fullName \ndoes not respect pattern", regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")
    private String fullName;
    @NotEmpty @Pattern(message = "cpfCnpj does not respect pattern", regexp = "^(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}|\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$")
    private String cpfCnpj;
    @NotEmpty @Pattern(message = "email does not respect pattern", regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")
    private String email;
    @NotEmpty @Pattern(message = "password does not respect pattern", regexp = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$")
    private String password;
}
