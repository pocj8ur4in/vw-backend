package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AuthMailFailException extends BaseCodeException { // 이메일 인증이 비활성화인 경우
	public static final BaseCodeException baseCodeException = new AuthMailFailException();

	private AuthMailFailException() {
		super(GlobalErrorCode.AUTH_MAIL_FAIL);
	}
}
