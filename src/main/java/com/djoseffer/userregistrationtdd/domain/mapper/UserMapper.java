package com.djoseffer.userregistrationtdd.domain.mapper;

import com.djoseffer.userregistrationtdd.domain.Dtos.LoginResponse;
import com.djoseffer.userregistrationtdd.domain.Dtos.RequestUserDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseAllUsersDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseDto;
import com.djoseffer.userregistrationtdd.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "senha")
    @Mapping(target = "firstName", source = "nome")
    @Mapping(target = "lastName", source = "sobrenome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "age", source = "idade")
    User convertDtoToUser(RequestUserDto userDto);

    @Mapping(target = "nome", source = "firstName")
    @Mapping(target = "sobrenome", source = "lastName")
    ResponseDto convertUserToResponseDto(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "firstName")
    @Mapping(target = "sobrenome", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "idade", source = "age")
    ResponseAllUsersDto convertUserToResponseallUsersDto(User user);

    @Mapping(target = "nome", source = "firstName")
    @Mapping(target = "sobrenome", source = "lastName")
    LoginResponse convertUserToLoginResponseDto(User user);

}
