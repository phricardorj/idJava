package br.com.phricardo.idJava.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterRequestDto  {
    private String username;
    private String fullName;
    private String cpfCnpj;
    private String email;
    private String password;
}
