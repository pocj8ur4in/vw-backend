package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AlreadyEmailAuthException extends BaseCodeException { // 이미 인증된 이메일 주소인 경우
	public static final AlreadyEmailAuthException baseCodeException =
			new AlreadyEmailAuthException();

	private AlreadyEmailAuthException() {
		super(GlobalErrorCode.ALREADY_EMAIL_AUTH);
	}
}
