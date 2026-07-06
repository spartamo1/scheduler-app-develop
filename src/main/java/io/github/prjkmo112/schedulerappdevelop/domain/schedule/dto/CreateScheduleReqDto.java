package io.github.prjkmo112.schedulerappdevelop.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateScheduleReqDto {

    @NotBlank
    @Size(max = 10, message = "제목은 10자 이하로 입력해주세요")
    private String title;

    @NotBlank
    @Size(max = 200, message = "내용은 200자 이하로 입력해주세요")
    private String content;

}
