package io.github.prjkmo112.schedulerappdevelop.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.CreateUserReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.service.UserService;
import io.github.prjkmo112.schedulerappdevelop.entity.User;
import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;
import io.github.prjkmo112.schedulerappdevelop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;

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

    @Transactional
    public UserDto signup(CreateUserReqDto createUserReqDto) {
        return userService.create(createUserReqDto);
    }

}
