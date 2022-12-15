package br.com.phricardo.idJava.Controller;

import br.com.phricardo.idJava.Dto.ErrorResponseDto;
import br.com.phricardo.idJava.Dto.RegisterAuthRequestDto;
import br.com.phricardo.idJava.Model.User;
import br.com.phricardo.idJava.Service.UserService;
import br.com.phricardo.idJava.Util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final JwtTokenUtil jwtUtil;
    private final UserService userService;

    public UserController(JwtTokenUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public User getUser(HttpServletRequest request) {
        return userService.getUserById(userService.getUserIdByHeader(request));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody RegisterAuthRequestDto userRequestDto) {
        User user = userService.getUserById(userService.getUserIdByHeader(request));
        ErrorResponseDto errorResponseDto = userService.validateCredentials(userRequestDto, user);
        if(errorResponseDto.isOk()) {
            return new ResponseEntity<>(userService.updateUserData(user, userRequestDto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }
}