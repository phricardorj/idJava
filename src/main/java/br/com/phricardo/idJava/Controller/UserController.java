package br.com.phricardo.idJava.Controller;

import br.com.phricardo.idJava.Model.User;
import br.com.phricardo.idJava.Repository.UserRepository;
import br.com.phricardo.idJava.Util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private final JwtTokenUtil jwtUtil;
    private final UserRepository userRepository;

    public UserController(JwtTokenUtil jwtUtil,UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public User getUser(HttpServletRequest request) {
        String[] jwtSubject = jwtUtil.getSubject(jwtUtil.getHeaderToken(request)).split(",");
        User user = userRepository.findByEmail(jwtSubject[1]).orElse(new User());
        return user;
    }
}