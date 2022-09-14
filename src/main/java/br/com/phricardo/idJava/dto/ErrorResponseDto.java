package br.com.phricardo.idJava.dto;

import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponseDto {
    private Integer numErros;
    private List<String> errors;
}
