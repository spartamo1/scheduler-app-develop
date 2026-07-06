package io.github.prjkmo112.schedulerappdevelop.domain.schedule.controller;

import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.prjkmo112.schedulerappdevelop.common.annotation.UserInfo;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.CreateScheduleReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto.ScheduleDto;
import io.github.prjkmo112.schedulerappdevelop.domain.schedule.service.ScheduleService;


@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<ScheduleDto> getAll(
            @RequestParam(required = false) String username
    ) {
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleDto getItem(
            @PathVariable Long id
    ) {
        return scheduleService.getItem(id);
    }

    @PostMapping
    public ScheduleDto create(
            @UserInfo Long userId,
            @Valid @RequestBody CreateScheduleReqDto createScheduleReqDto
    ) {
        return scheduleService.create(userId, createScheduleReqDto);
    }

    @PutMapping("/{id}")
    public ScheduleDto modify(
            @PathVariable Long id,
            @Valid @RequestBody CreateScheduleReqDto createScheduleReqDto
    ) {
        return scheduleService.modify(id, createScheduleReqDto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        scheduleService.delete(id);
    }

}
