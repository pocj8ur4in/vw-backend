package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AuthMailDeleteFailException extends BaseCodeException { // 인증 이메일 정보 삭제가 실패한 경우
	public static final BaseCodeException baseCodeException = new AuthMailDeleteFailException();

	private AuthMailDeleteFailException() {
		super(GlobalErrorCode.AUTH_MAIL_DELETE_FAIL);
	}
}
