package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class IncorrectIdLengthException extends BaseCodeException { // 아이디 길이가 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectIdLengthException();

	public IncorrectIdLengthException() {
		super(UserErrorCode.INCORRECT_ID_LENGTH);
	}
}
