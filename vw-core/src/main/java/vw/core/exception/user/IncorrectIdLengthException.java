package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class IncorrectIdLengthException extends BaseCodeException { // 아이디 길이가 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectIdLengthException();

	private IncorrectIdLengthException() {
		super(UserErrorCode.INCORRECT_ID_LENGTH);
	}
}
