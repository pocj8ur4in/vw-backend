package vw.domain.user.exception;

import static vw.core.statics.BaseStatic.NOT_FOUND;

import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import vw.core.annotation.ExplainError;
import vw.core.dto.BaseErrorReason;
import vw.core.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    @ExplainError("유저 정보를 찾을 수 없는 경우")
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "유저 정보를 찾을 수 없습니다.");

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
