package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateNicknameRequest {
    private String nickname; // 회원 닉네임
}
