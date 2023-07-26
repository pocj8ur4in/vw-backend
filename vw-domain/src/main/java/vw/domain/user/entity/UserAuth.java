package vw.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth { // 회원 인증
    @Column(name = "user_id", unique = true, length = 15)
    private String id; // 회원 아이디

    @Column(name = "user_password", length = 30)
    private String password; // 회원 비밀번호

    @Builder
    public UserAuth(String id, String password) { // Builder 패턴으로 UserAuth 객체를 생성 가능하게 함
        this.id = id;
        this.password = password;
    }

    public void setPassword(String password) { // 비밀번호 재설정
        this.password = password;
    }
}
