package com.djoseffer.userregistrationtdd.services.constants;

import com.djoseffer.userregistrationtdd.domain.Dtos.RequestUserDto;
import com.djoseffer.userregistrationtdd.domain.User;

public class UserConstants {
    public static final User USER1 = new User(1L, "senha", "nome", "sobrenome", "email", "idade");
    public static final RequestUserDto DTO = new RequestUserDto( "senha", "nome", "sobrenome", "email", "idade");
}
