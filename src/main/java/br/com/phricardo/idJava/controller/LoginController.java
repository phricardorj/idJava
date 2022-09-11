package br.com.phricardo.idJava.controller;

import br.com.phricardo.idJava.dto.UserLoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @PostMapping()
    public UserLoginRequestDto login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){
        return userLoginRequestDto;
    }

}
