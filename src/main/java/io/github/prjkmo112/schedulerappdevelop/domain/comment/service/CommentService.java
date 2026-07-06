package io.github.prjkmo112.schedulerappdevelop.domain.comment.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CommentDto;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CreateCommentReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.mapper.CommentMapper;
import io.github.prjkmo112.schedulerappdevelop.entity.Comment;
import io.github.prjkmo112.schedulerappdevelop.entity.Schedule;
import io.github.prjkmo112.schedulerappdevelop.entity.User;
import io.github.prjkmo112.schedulerappdevelop.repository.CommentRepository;
import io.github.prjkmo112.schedulerappdevelop.repository.ScheduleRepository;
import io.github.prjkmo112.schedulerappdevelop.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public List<CommentDto> getAll(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId).stream()
                .map(commentMapper::toCommentDto)
                .toList();
    }

    @Transactional
    public CommentDto create(Long userId, Long scheduleId, CreateCommentReqDto createCommentReqDto) {
        Comment comment = commentMapper.toEntity(createCommentReqDto);

        Schedule schedule = scheduleRepository.getReferenceById(scheduleId);
        comment.setSchedule(schedule);
        User user = userRepository.getReferenceById(userId);
        comment.setUser(user);

        commentRepository.save(comment);
        return commentMapper.toCommentDto(comment);
    }

}
