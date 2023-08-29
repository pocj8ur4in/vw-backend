package vw.core.exception.error;

import static vw.core.statics.BaseStatic.*;

import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import vw.core.annotation.ExplainError;
import vw.core.dto.error.BaseErrorReason;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
	// 인증 이메일 관련 오류
	@ExplainError("이메일 전송이 실패했을 때 발생하는 오류")
	EMAIL_SEND_FAIL(INTERNAL_SERVER, "EMAIL_500_1", "이메일 전송이 실패하였습니다."),
	@ExplainError("이미 전송된 이메일 주소인 경우 발생하는 오류")
	ALREADY_EMAIL_SEND(FORBIDDEN, "EMAIL_403_1", "해당 주소에 이미 인증 이메일이 전송되었습니다."),
	@ExplainError("이미 인증된 이메일 주소인 경우 발생하는 오류")
	ALREADY_EMAIL_AUTH(FORBIDDEN, "EMAIL_403_2", "해당 이메일은 이미 인증되었습니다."),
	@ExplainError("인증 키가 유효하지 않을 때 발생하는 오류")
	AUTH_KEY_INVALID(FORBIDDEN, "EMAIL_403_3", "인증 키가 유효하지 않습니다."),
	@ExplainError("이메일이 유효하지 않을 때 발생하는 오류")
	EMAIL_INVALID(FORBIDDEN, "EMAIL_403_4", "이메일이 유효하지 않습니다."),
	// JWT 관련 오류
	@ExplainError("토큰의 인증 시간이 만료되었을 때 발생하는 오류")
	TOKEN_EXPIRED(UNAUTHORIZED, "JWT_401_1", "토큰의 인증 시간이 만료되었습니다."),
	@ExplainError("토큰이 유효하지 않을 때 발생하는 오류")
	TOKEN_INVALID(UNAUTHORIZED, "JWT_401_2", "토큰이 유효하지 않습니다.");

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
