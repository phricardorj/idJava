package br.com.phricardo.idJava.service;

import br.com.phricardo.idJava.dto.ErrorResponseDto;
import br.com.phricardo.idJava.dto.UserRegisterRequestDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
import br.com.phricardo.idJava.util.PatternMatcherUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final PatternMatcherUtil patternMatcherUtil;

    public ResponseEntity<?> register(@Valid @RequestBody @NotNull UserRegisterRequestDto userRegisterRequestDto) {
            if(!isValidUserRequest(userRegisterRequestDto)) {
                return new ResponseEntity<>(new ErrorResponseDto("Some field does not respect the required pattern"), HttpStatus.BAD_REQUEST);
            }

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

    public Boolean isValidUserRequest(UserRegisterRequestDto userRegisterRequestDto){
        Boolean username = patternMatcherUtil.matcher("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",
                userRegisterRequestDto.getUsername());
        Boolean fullName = patternMatcherUtil.matcher("^(?:[\\p{L}\\p{Mn}\\p{Pd}\\'\\x{2019}]+(?:$|\\s+)){2,}$",
                userRegisterRequestDto.getFullName());
        Boolean cpfCnpj = patternMatcherUtil.matcher("^(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2}|\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$",
                userRegisterRequestDto.getCpfCnpj());
        Boolean email = patternMatcherUtil.matcher("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
                userRegisterRequestDto.getEmail());
        Boolean password = patternMatcherUtil.matcher("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$",
                userRegisterRequestDto.getPassword());
        Boolean isValid = username && fullName && cpfCnpj && email && password;
                return isValid;
    }

}
