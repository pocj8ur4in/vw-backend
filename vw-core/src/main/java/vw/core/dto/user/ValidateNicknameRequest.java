package vw.core.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateNicknameRequest {
    @NotBlank(message = "닉네임 입력은 필수입니다.")
    private String nickname; // 회원 닉네임
}
