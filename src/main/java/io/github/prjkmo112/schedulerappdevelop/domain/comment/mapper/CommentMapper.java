package io.github.prjkmo112.schedulerappdevelop.domain.comment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CommentDto;
import io.github.prjkmo112.schedulerappdevelop.domain.comment.dto.CreateCommentReqDto;
import io.github.prjkmo112.schedulerappdevelop.domain.user.mapper.UserMapper;
import io.github.prjkmo112.schedulerappdevelop.entity.Comment;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CommentMapper {

    CommentDto toCommentDto(Comment comment);

    Comment toEntity(CreateCommentReqDto createCommentReqDto);

}
