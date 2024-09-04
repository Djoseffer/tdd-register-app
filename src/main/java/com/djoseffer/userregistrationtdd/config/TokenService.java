package com.djoseffer.userregistrationtdd.config;

import com.djoseffer.userregistrationtdd.domain.Dtos.LoginDto;
import com.djoseffer.userregistrationtdd.domain.Dtos.LoginResponse;
import com.djoseffer.userregistrationtdd.domain.User;
import com.djoseffer.userregistrationtdd.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenService {

    private UserRepository userRepository;
    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse authenticateUser(LoginDto login) {
        var user = userRepository.findByEmail(login.email());
        if (user.isEmpty() || !isLoginCorrect(user.get(), login)) {
            throw new BadCredentialsException("Invalid email or password");
        }

        var now = Instant.now();
        var expiresIn = 1000L;

        var claims = JwtClaimsSet.builder()
                .issuer("back-end")
                .subject(user.get().getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("id", user.get().getId())
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, user.get().getFirstName(), user.get().getLastName(), expiresIn);
    }

    private boolean isLoginCorrect(User user, LoginDto login) {
        return bCryptPasswordEncoder.matches(login.password(), user.getPassword());
    }
}
