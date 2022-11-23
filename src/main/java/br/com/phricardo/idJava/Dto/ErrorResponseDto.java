package br.com.phricardo.idJava.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponseDto {
    private boolean ok;
    private String message;
    private List<String> fields;
}
