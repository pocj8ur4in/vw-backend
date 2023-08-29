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
public enum UserErrorCode implements BaseErrorCode {
	@ExplainError("아이디가 이미 존재하는 경우")
	ALREADY_EXIST_ID(BAD_REQUEST, "USER_400_1", "해당 아이디가 이미 존재합니다."),
	@ExplainError("이메일이 이미 존재하는 경우")
	ALREADY_EXIST_EMAIL(BAD_REQUEST, "USER_400_2", "해당 이메일이 이미 존재합니다."),
	@ExplainError("닉네임이 이미 존재하는 경우")
	ALREADY_EXIST_NICKNAME(BAD_REQUEST, "USER_400_3", "해당 닉네임이 이미 존재합니다."),
	@ExplainError("아이디가 공란인 경우")
	EMPTY_ID(BAD_REQUEST, "USER_400_4", "아이디를 입력해주세요."),
	@ExplainError("이메일이 공란인 경우")
	EMPTY_EMAIL(BAD_REQUEST, "USER_400_5", "이메일을 입력해주세요."),
	@ExplainError("닉네임이 공란인 경우")
	EMPTY_NICKNAME(BAD_REQUEST, "USER_400_6", "닉네임을 입력해주세요."),
	@ExplainError("아이디의 길이가 잘못된 경우")
	INCORRECT_ID_LENGTH(BAD_REQUEST, "USER_400_7", "아이디 길이가 잘못되었습니다. 5~16자로 해주세요."),
	@ExplainError("닉네임의 길이가 잘못된 경우")
	INCORRECT_NICKNAME_LENGTH(BAD_REQUEST, "USER_400_8", "닉네임 길이가 잘못되었습니다. 1~12자로 해주세요."),
	@ExplainError("아이디에 알파벳 대소문자와 숫자 외의 문자가 사용된 경우")
	INCORRECT_ID(BAD_REQUEST, "USER_400_9", "아이디 형식이 잘못되었습니다. 알파벳 대소문자와 숫자만 사용 가능합니다."),
	@ExplainError("닉네임에 알파벳 대소문자, 한글, 숫자 외의 문자가 사용된 경우")
	INCORRECT_NICKNAME(BAD_REQUEST, "USER_400_10", "닉네임 형식이 잘못되었습니다. 한글, 알파벳 대소문자, 숫자만 사용 가능합니다."),
	@ExplainError("이메일의 형식이 잘못된 경우")
	INCORRECT_EMAIL(BAD_REQUEST, "USER_400_11", "이메일 형식이 잘못되었습니다."),
	@ExplainError("비밀번호 형식이 잘못된 경우")
	INCORRECT_PASSWORD(BAD_REQUEST, "USER_400_12", "비밀번호 형식이 잘못되었습니다."),
	@ExplainError("비밀번호와 비밀번호 확인이 일치하지 않는 경우")
	DIFFERENT_PASSWORD(BAD_REQUEST, "USER_400_13", "입력한 비밀번호와 비밀번호 확인이 서로 일치하지 않습니다."),
	@ExplainError("사용자 정보를 찾을 수 없는 경우")
	USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "사용자 정보를 찾을 수 없습니다."),
	@ExplainError("사용자의 회원가입이 실패한 경우")
	REGISTER_FAILURE(INTERNAL_SERVER, "USER_500_1", "회원가입이 실패하였습니다"),
	@ExplainError("사용자의 로그인이 실패한 경우")
	LOGIN_FAILURE(INTERNAL_SERVER, "USER_500_2", "로그인이 실패하였습니다.");

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
		ExplainError explainError = field.getAnnotation(ExplainError.class);
		return Objects.nonNull(explainError) ? explainError.value() : this.getReason();
	}
}
