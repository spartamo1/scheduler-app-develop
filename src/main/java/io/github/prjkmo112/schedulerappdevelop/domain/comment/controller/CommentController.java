package io.github.prjkmo112.schedulerappdevelop.domain.comment.controller;

import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.prjkmo112.schedulerappdevelop.common.annotation.UserInfo;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CommentDto;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CreateCommentReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.service.CommentService;

@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getAll(@PathVariable Long scheduleId) {
        return commentService.getAll(scheduleId);
    }

    @PostMapping
    public CommentDto create(
            @UserInfo Long userId,
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentReqDto createCommentReqDto
    ) {
        return commentService.create(userId, scheduleId, createCommentReqDto);
    }

}
