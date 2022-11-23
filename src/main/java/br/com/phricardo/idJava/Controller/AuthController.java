package br.com.phricardo.idJava.Controller;

import br.com.phricardo.idJava.Dto.*;
import br.com.phricardo.idJava.Model.User;
import br.com.phricardo.idJava.Repository.UserRepository;
import br.com.phricardo.idJava.Service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    private final UserService userService;

    public AuthController(JwtTokenUtil jwtUtil, PasswordEncoder encoder, UserRepository userRepository, AuthenticationManager authManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginAuthRequestDto request) {
        try {
            UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword());

            Authentication authentication = authManager.authenticate(loginData);
            User user = (User) authentication.getPrincipal();

            AuthResponse response = AuthResponse.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .accessToken(jwtUtil.generateAccessToken(user))
                    .build();

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> userRegister(@RequestBody RegisterAuthRequestDto registerAuthRequestDto) {
        ErrorResponseDto errorResponseDto = userService.validateRegister(registerAuthRequestDto);
        if(errorResponseDto.isOk()) {
            User newUser = User.builder()
                    .name(registerAuthRequestDto.getName())
                    .username(registerAuthRequestDto.getUsername())
                    .email(registerAuthRequestDto.getEmail())
                    .password(encoder.encode(registerAuthRequestDto.getPassword()))
                    .build();

            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }

    @PostMapping("/auth/validate-token")
    public Boolean validateAccessToken(HttpServletRequest request) {
        String token = jwtUtil.getHeaderToken(request);
        return jwtUtil.validateAccessToken(token);
    }
}