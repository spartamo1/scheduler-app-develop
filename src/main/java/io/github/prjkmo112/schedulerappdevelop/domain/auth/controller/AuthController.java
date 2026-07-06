package io.github.prjkmo112.schedulerappdevelop.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.prjkmo112.schedulerappdevelop.domain.auth.dto.LoginReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.auth.service.AuthService;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.CreateUserReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${spring.session.key.user-id}")
    private String sessionKeyUserId;

    @Value("${spring.session.key.user-email}")
    private String sessionKeyUserEmail;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginReqDto loginReqDto, HttpServletRequest request) {
        AuthService.AuthUserDto authUserDto = authService.login(loginReqDto.getEmail(), loginReqDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(sessionKeyUserId, authUserDto.id());
        session.setAttribute(sessionKeyUserEmail, authUserDto.email());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody CreateUserReqDto createUserReqDto) {
        return ResponseEntity.ok(authService.signup(createUserReqDto));
    }

}
