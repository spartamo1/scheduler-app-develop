package io.github.prjkmo112.schedulerappdevelop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prjkmo112.schedulerappdevelop.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}