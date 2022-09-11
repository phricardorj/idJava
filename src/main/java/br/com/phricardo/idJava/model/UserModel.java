package br.com.phricardo.idJava.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserModel {
    private UUID id;
    private String username;
    private String fullName;
    private String cpfCnpj;
    private String email;
    private String password;
    private Boolean active;
}
