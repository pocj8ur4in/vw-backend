package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class InvalidEmailException extends BaseCodeException { // 이메일이 유효하지 않은 경우
	public static final InvalidEmailException baseCodeException = new InvalidEmailException();

	private InvalidEmailException() {
		super(GlobalErrorCode.EMAIL_INVALID);
	}
}
