package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class IncorrectPasswordException extends BaseCodeException { // 비밀번호 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectPasswordException();

	public IncorrectPasswordException() {
		super(UserErrorCode.INCORRECT_PASSWORD);
	}
}