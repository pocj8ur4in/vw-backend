package vw.core.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateIdRequest {
    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String id; // 회원 아이디
}
