package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class LoginFailureException extends BaseCodeException { // 로그인이 실패한 경우
	public static final BaseCodeException baseCodeException = new LoginFailureException();

	private LoginFailureException() {
		super(UserErrorCode.LOGIN_FAILURE);
	}
}
