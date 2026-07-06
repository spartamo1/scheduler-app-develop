package io.github.prjkmo112.schedulerappdevelop.domain.schedule.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.CreateScheduleReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.ScheduleDto;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.mapper.ScheduleMapper;
import io.github.prjkmo112.schedulerappdevelop.entity.Schedule;
import io.github.prjkmo112.schedulerappdevelop.entity.User;
import io.github.prjkmo112.schedulerappdevelop.exception.ApiException;
import io.github.prjkmo112.schedulerappdevelop.repository.ScheduleRepository;
import io.github.prjkmo112.schedulerappdevelop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final UserRepository userRepository;

    public List<ScheduleDto> getAll() {
        return scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")).stream()
                .map(scheduleMapper::toScheduleDto)
                .toList();
    }

    public ScheduleDto getItem(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApiException("Schedule not found", HttpStatus.NOT_FOUND));

        return scheduleMapper.toScheduleDto(schedule);
    }

    @Transactional
    public ScheduleDto create(Long userId, CreateScheduleReqDto createScheduleReqDto) {
        Schedule schedule = scheduleMapper.toEntity(createScheduleReqDto);
        User user = userRepository.getReferenceById(userId);
        schedule.setUser(user);
        scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleDto(schedule);
    }

    @Transactional
    public ScheduleDto modify(Long scheduleId, CreateScheduleReqDto createScheduleReqDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ApiException("Schedule not found", HttpStatus.NOT_FOUND));

        schedule.setTitle(createScheduleReqDto.getTitle());
        schedule.setContent(createScheduleReqDto.getContent());
        scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleDto(schedule);
    }

    @Transactional
    public void delete(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

}
