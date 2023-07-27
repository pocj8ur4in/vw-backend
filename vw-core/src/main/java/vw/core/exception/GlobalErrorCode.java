package vw.core.exception;

import static vw.core.statics.BaseStatic.UNAUTHORIZED;

import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import vw.core.annotation.ExplainError;
import vw.core.dto.BaseErrorReason;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    // JWT 관련 오류
    @ExplainError("토큰의 인증 시간이 만료되었을 때 발생하는 오류")
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "토큰의 인증 시간이 만료되었습니다."),
    @ExplainError("토큰이 유효하지 않을 때 발생하는 오류")
    TOKEN_INVALID(UNAUTHORIZED, "AUTH_401_2", "토큰이 유효하지 않습니다.");

    private final Integer status; // HTTP 상태 코드
    private final String code; // HTTP 상태 코드 (문자열)
    private final String reason; // 오류의 원인

    @Override
    public BaseErrorReason getErrorReason() {
        return BaseErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);

        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
