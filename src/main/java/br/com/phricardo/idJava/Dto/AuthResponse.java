package br.com.phricardo.idJava.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthResponse {
    private String username;
    private String email;
    private String accessToken;
}
