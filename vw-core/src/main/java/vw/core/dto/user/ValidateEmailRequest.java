package vw.core.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateEmailRequest {
    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email; // 회원 이메일
}
