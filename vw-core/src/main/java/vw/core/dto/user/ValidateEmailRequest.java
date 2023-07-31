package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateEmailRequest {
    private String email; // 회원 이메일
}
