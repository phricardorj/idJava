package br.com.phricardo.idJava.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponseDto {
    private Integer numErros;
    private String errors;
}