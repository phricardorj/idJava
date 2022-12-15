package br.com.phricardo.idJava.Service;

import br.com.phricardo.idJava.Dto.ErrorResponseDto;
import br.com.phricardo.idJava.Dto.RegisterAuthRequestDto;
import br.com.phricardo.idJava.Model.User;
import br.com.phricardo.idJava.Repository.UserRepository;
import br.com.phricardo.idJava.Util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final JwtTokenUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtTokenUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User updateUserData(User user, RegisterAuthRequestDto userRequestDto) {
        user.setName(userRequestDto.getName());
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        return userRepository.save(user);
    }

    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).orElse(new User());
    }

    public UUID getUserIdByHeader(HttpServletRequest request) {
        String token = jwtUtil.getHeaderToken(request);
        return jwtUtil.getUserIdByToken(token);
    }

    public ErrorResponseDto validateCredentials(RegisterAuthRequestDto userRequest, User userLogado) {
        String username = userRequest.getUsername();
        String email = userRequest.getEmail();

        List<String> fields = new ArrayList<>();
        if(!username.equals(userLogado.getUsername()) && !email.equals(userLogado.getEmail())) {
            if(userRepository.findByEmail(email).isPresent() && userRepository.findByUsername(username).isPresent()) {
                fields.add("email");
                fields.add("username");
                return new ErrorResponseDto(false, "Email e Usuário já cadastrado", fields);
            }
        }
        if(!email.equals(userLogado.getEmail())) {
            if (userRepository.findByEmail(email).isPresent()) {
                fields.add("email");
                return new ErrorResponseDto(false, "Email já cadastrado", fields);
            }
        }
        if(!username.equals(userLogado.getUsername())) {
            if (userRepository.findByUsername(username).isPresent()) {
                fields.add("username");
                return new ErrorResponseDto(false, "Usuario ja cadastrado", fields);
            }
        }

        return new ErrorResponseDto(true, "", fields);
    }

    public ErrorResponseDto validateRegister(RegisterAuthRequestDto registerAuthRequestDto) {
        List<String> fields = new ArrayList<>();
        if (userRepository.findByEmail(registerAuthRequestDto.getEmail()).isPresent() && userRepository.findByUsername(registerAuthRequestDto.getUsername()).isPresent()) {
            fields.add("email");
            fields.add("username");
            return new ErrorResponseDto(false, "Email e Usuário já cadastrado", fields);
        }
        if (userRepository.findByEmail(registerAuthRequestDto.getEmail()).isPresent()) {
            fields.add("email");
            return new ErrorResponseDto(false, "Email já cadastrado", fields);
        }
        if (userRepository.findByUsername(registerAuthRequestDto.getUsername()).isPresent()) {
            fields.add("username");
            return new ErrorResponseDto(false, "Usuario ja cadastrado", fields);
        }
        return new ErrorResponseDto(true, "", fields);
    }
}
