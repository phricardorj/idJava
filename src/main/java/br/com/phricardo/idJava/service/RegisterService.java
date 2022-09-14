package br.com.phricardo.idJava.service;

import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public ResponseEntity<?> register(UserRegisterRequestDto userRegisterRequestDto) {
            UserModel userModel = userRepository.save(
                    UserModel.builder()
                            .userId(UUID.randomUUID())
                            .username(userRegisterRequestDto.getUsername())
                            .fullName(userRegisterRequestDto.getFullName())
                            .cpfCnpj(userRegisterRequestDto.getCpfCnpj())
                            .email(userRegisterRequestDto.getEmail())
                            .password(encoder.encode(userRegisterRequestDto.getPassword()))
                            .active(true)
                            .build()
            );

            return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }
}
