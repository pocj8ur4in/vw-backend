package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class EmptyIdException extends BaseCodeException { // 아이디가 공란인 경우
	public static final BaseCodeException baseCodeException = new EmptyIdException();

	public EmptyIdException() {
		super(UserErrorCode.EMPTY_ID);
	}
}
