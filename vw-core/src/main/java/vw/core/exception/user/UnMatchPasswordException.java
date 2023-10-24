package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class UnMatchPasswordException extends BaseCodeException { // 입력한 비밀번호가 틀린 경우
	public static final BaseCodeException baseCodeException = new UnMatchPasswordException();

	private UnMatchPasswordException() {
		super(UserErrorCode.UN_MATCH_PASSWORD);
	}
}
