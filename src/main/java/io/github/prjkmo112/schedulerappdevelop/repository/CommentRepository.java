package io.github.prjkmo112.schedulerappdevelop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prjkmo112.schedulerappdevelop.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);
}