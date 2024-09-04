package com.djoseffer.userregistrationtdd.services;

import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseAllUsersDto;
import com.djoseffer.userregistrationtdd.domain.User;
import com.djoseffer.userregistrationtdd.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.djoseffer.userregistrationtdd.services.constants.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateUserWithSuccess() {

            doReturn(USER1).when(userRepository).save(userArgumentCaptor.capture());
            var output = userService.createUser(DTO);
            assertNotNull(output);
            var user = userArgumentCaptor.getValue();
            assertEquals(DTO.getNome(), user.getFirstName());
            assertEquals(DTO.getSobrenome(), user.getLastName());
            assertEquals(DTO.getEmail(), user.getEmail());
            assertEquals(DTO.getSenha(), user.getPassword());
            assertEquals(DTO.getIdade(), user.getAge());
        }
    }

    @Nested
    class getAllUsers {

        @Test
        @DisplayName("Should list users successfully ")
        void shouldListUsersSuccessfully() {
            User user = new User(USER1.getId(), USER1.getPassword(), USER1.getFirstName(), USER1.getLastName(), USER1.getEmail(), USER1.getAge());
            doReturn(List.of(user)).when(userRepository).findAll();
            var expectedDto = new ResponseAllUsersDto(USER1.getId(), USER1.getFirstName(), USER1.getLastName(), USER1.getEmail(), USER1.getAge());
            List<ResponseAllUsersDto> result = userService.getAllUsers();

            assertEquals(1, result.size());

            ResponseAllUsersDto actualDto = result.getFirst();
            assertEquals(expectedDto.getId(), actualDto.getId());
            assertEquals(expectedDto.getNome(), actualDto.getNome());
            assertEquals(expectedDto.getSobrenome(), actualDto.getSobrenome());
            assertEquals(expectedDto.getEmail(), actualDto.getEmail());
            assertEquals(expectedDto.getIdade(), actualDto.getIdade());
        }
    }
}