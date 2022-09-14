package br.com.phricardo.idJava.service;

import br.com.phricardo.idJava.dto.UserLoginRequestDto;
import br.com.phricardo.idJava.model.UserModel;
import br.com.phricardo.idJava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<?> isValid(UserLoginRequestDto userLoginRequestDto) {
        String username = userLoginRequestDto.getUsername();
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();

        if(!email.isEmpty() || !username.isEmpty()){
            Optional<UserModel> user = userRepository.findByEmail(email).isPresent() ? userRepository.findByEmail(email) : userRepository.findByUsername(username);
            UserModel userModel = user.orElse(null);
            if(userModel != null) return new ResponseEntity<>(encoder.matches(password, userModel.getPassword()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username or Email needs to be informed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Username or Email not found in the system", HttpStatus.BAD_REQUEST);
    }

}

