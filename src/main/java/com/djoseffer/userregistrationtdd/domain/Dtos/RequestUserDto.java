package com.djoseffer.userregistrationtdd.domain.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
    private String senha;
    private String nome;
    private String sobrenome;
    private String email;
    private String idade;
}
