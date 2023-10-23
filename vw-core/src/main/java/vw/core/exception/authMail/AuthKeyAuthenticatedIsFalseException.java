package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AuthKeyAuthenticatedIsFalseException extends BaseCodeException { // 이메일 인증 여부가 비활성화인 경우
	public static final BaseCodeException baseCodeException =
			new AuthKeyAuthenticatedIsFalseException();

	private AuthKeyAuthenticatedIsFalseException() {
		super(GlobalErrorCode.AUTH_KEY_IS_FALSE);
	}
}
