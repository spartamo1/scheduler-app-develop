package io.github.prjkmo112.schedulerappdevelop.domain.user.controller;

import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.CreateUserReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getItem(
            @PathVariable Long id
    ) {
        return userService.getItem(id);
    }

    @PutMapping("/{id}")
    public UserDto modify(
            @PathVariable Long id,
            @Valid @RequestBody CreateUserReqDto createUserReqDto
    ) {
        return userService.modify(id, createUserReqDto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

}
