package io.github.prjkmo112.schedulerappdevelop.domain.schedule.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.github.prjkmo112.schedulerappdevelop.domain.comment.mapper.CommentMapper;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.CreateScheduleReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.ScheduleDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.mapper.UserMapper;
import io.github.prjkmo112.schedulerappdevelop.entity.Schedule;


@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, CommentMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ScheduleMapper {

    ScheduleDto toScheduleDto(Schedule schedule);

    Schedule toEntity(CreateScheduleReqDto createScheduleReqDto);

}
