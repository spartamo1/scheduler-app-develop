package io.github.prjkmo112.schedulerappdevelop.domain.user.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.CreateReqUserDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.mapper.UserMapper;
import io.github.prjkmo112.schedulerappdevelop.entity.User;
import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;
import io.github.prjkmo112.schedulerappdevelop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto getItem(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User Not Found", HttpStatus.NOT_FOUND));

        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDto create(CreateReqUserDto createReqUserDto) {
        User user = userMapper.toEntity(createReqUserDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDto modify(Long userId, CreateReqUserDto createReqUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User Not Found", HttpStatus.NOT_FOUND));

        user.setName(createReqUserDto.getName());
        user.setPassword(createReqUserDto.getPassword());
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
