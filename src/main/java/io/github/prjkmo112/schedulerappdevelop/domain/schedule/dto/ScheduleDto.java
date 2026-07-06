package io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.github.prjkmo112.schedulerappdevelop.common.dto.AuditingBaseDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.dto.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto extends AuditingBaseDto {

    private Long id;

    private String title;

    private String content;

    private UserDto user;

}
