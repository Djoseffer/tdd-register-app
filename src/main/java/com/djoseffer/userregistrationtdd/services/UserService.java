package com.djoseffer.userregistrationtdd.services;

import com.djoseffer.userregistrationtdd.domain.Dtos.RequestUserDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseAllUsersDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.ResponseDto;
import com.djoseffer.userregistrationtdd.domain.User;
import com.djoseffer.userregistrationtdd.domain.mapper.UserMapper;
import com.djoseffer.userregistrationtdd.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ResponseDto createUser(RequestUserDto userDto) {
        User user = UserMapper.INSTANCE.convertDtoToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserMapper.INSTANCE.convertUserToResponseDto(user);
    }

    public List<ResponseAllUsersDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::convertUserToResponseallUsersDto)
                .collect(Collectors.toList());
    }

    public ResponseAllUsersDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        return UserMapper.INSTANCE.convertUserToResponseallUsersDto(user);
    }
}
