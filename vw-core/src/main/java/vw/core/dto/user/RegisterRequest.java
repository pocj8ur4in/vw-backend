package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {
    private String id; // 회원 아이디
    private String password; // 회원 비밀번호
    private String nickname; // 회원 닉네임
    private String email; // 회원 이메일
    private Boolean receiveEmail; // 회원 이메일 수신 여부
}
