package io.github.prjkmo112.schedulerappdevelop.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.github.prjkmo112.schedulerappdevelop.common.dto.AuditingBaseDto;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends AuditingBaseDto {

    private String name;

    private String email;

}
