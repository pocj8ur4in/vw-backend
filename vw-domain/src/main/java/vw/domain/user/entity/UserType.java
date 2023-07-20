package vw.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType { // 회원 유형
    TYPE_NORMAL("TYPE_NORMAL"), // 일반 회원 유형
    TYPE_ADMIN("TYPE_ADMIN"); // 관리자 회원 유형

    private String value; // 회원 유형 값
}
