package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class InvalidAuthKeyException extends BaseCodeException { // 인증 키가 유효하지 않은 경우
	public static final InvalidAuthKeyException baseCodeException = new InvalidAuthKeyException();

	private InvalidAuthKeyException() {
		super(GlobalErrorCode.AUTH_KEY_INVALID);
	}
}
