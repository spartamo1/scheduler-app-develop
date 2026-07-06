package io.github.prjkmo112.schedulerappdevelop.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 40)
    private String password;

}
