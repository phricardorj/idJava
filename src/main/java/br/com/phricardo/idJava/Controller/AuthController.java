package br.com.phricardo.idJava.Controller;

import br.com.phricardo.idJava.Dto.LoginAuthRequestDto;
import br.com.phricardo.idJava.Dto.AuthResponse;
import br.com.phricardo.idJava.Dto.RegisterAuthRequestDto;
import br.com.phricardo.idJava.Dto.TokenRequestDto;
import br.com.phricardo.idJava.Model.User;
import br.com.phricardo.idJava.Repository.UserRepository;
import br.com.phricardo.idJava.Util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthController(JwtTokenUtil jwtUtil, PasswordEncoder encoder, UserRepository userRepository, AuthenticationManager authManager) {
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authManager = authManager;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginAuthRequestDto request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/register")
    public User userRegister(@RequestBody RegisterAuthRequestDto registerAuthRequestDto) {
        User newUser = new User(
                registerAuthRequestDto.getName(),
                registerAuthRequestDto.getUsername(),
                registerAuthRequestDto.getEmail(),
                encoder.encode(registerAuthRequestDto.getPassword())
        );
        userRepository.save(newUser);
        return newUser;
    }

    @PostMapping("/auth/validate-token")
    public Boolean validateAccessToken(@RequestBody TokenRequestDto tokenRequestDTO) {
        return jwtUtil.validateAccessToken(tokenRequestDTO.getAccessToken());
    }

}