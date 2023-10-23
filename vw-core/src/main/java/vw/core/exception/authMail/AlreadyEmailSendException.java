package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AlreadyEmailSendException extends BaseCodeException { // 이미 전송된 이메일인 경우
	public static final BaseCodeException baseCodeException = new AlreadyEmailSendException();

	private AlreadyEmailSendException() {
		super(GlobalErrorCode.ALREADY_EMAIL_SEND);
	}
}
