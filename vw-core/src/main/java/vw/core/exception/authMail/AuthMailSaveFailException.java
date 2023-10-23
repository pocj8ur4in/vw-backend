package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AuthMailSaveFailException extends BaseCodeException { // 인증 이메일 정보 저장이 실패한 경우
	public static final BaseCodeException baseCodeException = new AuthMailSaveFailException();

	private AuthMailSaveFailException() {
		super(GlobalErrorCode.AUTH_MAIL_SAVE_FAIL);
	}
}
