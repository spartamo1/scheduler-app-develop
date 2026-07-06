package io.github.prjkmo112.schedulerappdevelop.domain.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.github.prjkmo112.schedulerappdevelop.common.dto.AuditingBaseDto;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto extends AuditingBaseDto {

    private String content;

}
