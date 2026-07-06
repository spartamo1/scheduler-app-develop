package io.github.prjkmo112.schedulerappdevelop.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.CreateUserReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;
import io.github.prjkmo112.schedulerappdevelop.entity.User;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toEntity(CreateUserReqDto createUserReqDto);

}
