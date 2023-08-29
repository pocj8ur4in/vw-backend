package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class RegisterFailureException extends BaseCodeException { // 사용자의 회원가입이 실패한 경우
	public static final BaseCodeException baseCodeException = new RegisterFailureException();

	public RegisterFailureException() {
		super(UserErrorCode.REGISTER_FAILURE);
	}
}
