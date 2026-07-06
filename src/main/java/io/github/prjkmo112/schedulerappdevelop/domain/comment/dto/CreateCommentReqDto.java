package io.github.prjkmo112.schedulerappdevelop.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentReqDto {

    @NotBlank
    @Size(max = 100)
    private String content;

}
