package vw.domain.user.exception;

import static vw.core.statics.BaseStatic.*;

import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import vw.core.annotation.ExplainError;
import vw.core.dto.error.BaseErrorReason;
import vw.core.exception.error.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    @ExplainError("사용자 정보를 찾을 수 없는 경우")
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "사용자 정보를 찾을 수 없습니다."),
    @ExplainError("아이디 길이가 너무 긴 경우")
    TOO_LONG_ID(BAD_REQUEST, "USER_400_1", "아이디의 길이가 너무 깁니다."),
    @ExplainError("아이디가 이미 존재하는 경우")
    ALREADY_EXIST_ID(BAD_REQUEST, "USER_400_2", "해당 아이디가 이미 존재합니다."),
    @ExplainError("이메일이 이미 존재하는 경우")
    ALREADY_EXIST_EMAIL(BAD_REQUEST, "USER_400_3", "해당 아이디가 이미 존재합니다."),
    @ExplainError("닉네임 길이가 너무 긴 경우")
    TOO_LONG_NICKNAME(BAD_REQUEST, "USER_400_4", "닉네임의 길이가 너무 깁니다."),
    @ExplainError("닉네임이 이미 존재하는 경우")
    ALREADY_EXIST_NICKNAME(BAD_REQUEST, "USER_400_5", "해당 닉네임이 이미 존재합니다."),
    @ExplainError("사용자의 회원가입이 실패한 경우")
    REGISTER_FAILURE(INTERNAL_SERVER, "USER_500_1", "회원가입이 실패하였습니다"),
    @ExplainError("사용자의 로그인이 실패한 경우")
    LOGIN_FAILURE(FORBIDDEN, "USER_403_1", "로그인이 실패하였습니다.");

    private Integer status; // HTTP 상태 코드
    private String code; // HTTP 상태 코드 (문자열)
    private String reason; // 오류의 원인

    @Override
    public BaseErrorReason getErrorReason() {
        return BaseErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError explainError = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(explainError) ? explainError.value() : this.getReason();
    }
}
