package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AuthMailUpdateFailException extends BaseCodeException { // 인증 이메일 정보 갱신이 실패한 경우
	public static final BaseCodeException baseCodeException = new AuthMailUpdateFailException();

	private AuthMailUpdateFailException() {
		super(GlobalErrorCode.AUTH_MAIL_UPDATE_FAIL);
	}
}
