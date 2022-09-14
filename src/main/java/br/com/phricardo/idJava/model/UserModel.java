package br.com.phricardo.idJava.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "userRegister")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String cpfCnpj;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active;
}
