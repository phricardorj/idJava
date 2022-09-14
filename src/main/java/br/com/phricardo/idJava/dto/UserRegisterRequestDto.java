package br.com.phricardo.idJava.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterRequestDto  {
    public static final String MESSAGE_PATTERN_ERROR = "not respect pattern";

    @NotEmpty @Pattern(message = MESSAGE_PATTERN_ERROR, regexp = "^[\\w](?!.*?\\.{2})[\\w.]{1,28}[\\w]$")
    private String username;
    @NotBlank @Pattern(message = MESSAGE_PATTERN_ERROR, regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$")
    private String fullName;
    @NotEmpty @Pattern(message = MESSAGE_PATTERN_ERROR, regexp = "^(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}|\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$")
    private String cpfCnpj;
    @NotEmpty @Pattern(message = MESSAGE_PATTERN_ERROR, regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")
    private String email;
    @NotEmpty @Pattern(message = MESSAGE_PATTERN_ERROR, regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
}
