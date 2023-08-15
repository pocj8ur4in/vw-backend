package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class IncorrectEmailException extends BaseCodeException { // 이메일 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectEmailException();

	public IncorrectEmailException() {
		super(UserErrorCode.INCORRECT_EMAIL);
	}
}
