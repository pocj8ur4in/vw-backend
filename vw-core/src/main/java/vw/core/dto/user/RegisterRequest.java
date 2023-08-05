package vw.core.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {
    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String id; // 회원 아이디

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password; // 회원 비밀번호

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    private String nickname; // 회원 닉네임

    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email; // 회원 이메일

    private Boolean receiveEmail; // 회원 이메일 수신 여부
}
