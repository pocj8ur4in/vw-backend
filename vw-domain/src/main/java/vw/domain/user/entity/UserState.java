package vw.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserState { // 회원 상태
    STATE_NORMAL("STATE_NORMAL"), // 일반 회원 상태
    STATE_RESIGN("STATE_RESIGN"), // 탈퇴 회원 상태
    STATE_BANNED("STATE_BANNED"); // 정지 회원 상태

    private String value; // 회원 상태 값
}
