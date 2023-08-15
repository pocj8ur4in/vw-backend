package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class IncorrectIdException extends BaseCodeException { // 아이디 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectIdException();

	public IncorrectIdException() {
		super(UserErrorCode.INCORRECT_ID);
	}
}
