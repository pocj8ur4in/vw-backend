package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class IncorrectIdException extends BaseCodeException { // 아이디 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectIdException();

	public IncorrectIdException() {
		super(UserErrorCode.INCORRECT_ID);
	}
}
