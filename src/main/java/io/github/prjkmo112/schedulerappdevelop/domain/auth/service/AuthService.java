package io.github.prjkmo112.schedulerappdevelop.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.prjkmo112.schedulerappdevelop.entity.User;
import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;
import io.github.prjkmo112.schedulerappdevelop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    public record AuthUserDto(Long id, String email) {
    }

    public AuthUserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("Email or Password not match", HttpStatus.UNAUTHORIZED));

        if (!user.getPassword().equals(password)) {
            throw new ApiException("Email or Password not match", HttpStatus.UNAUTHORIZED);
        }

        return new AuthUserDto(user.getId(), user.getEmail());
    }

}
