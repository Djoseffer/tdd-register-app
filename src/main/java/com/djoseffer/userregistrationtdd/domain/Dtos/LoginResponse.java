package com.djoseffer.userregistrationtdd.domain.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String nome;
    private String sobrenome;
    private Long expiresAt;
}
