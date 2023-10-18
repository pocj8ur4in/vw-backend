package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class EmailSendFailException extends BaseCodeException { // 이메일 전소이 실패한 경우
	public static final BaseCodeException baseCodeException = new EmailSendFailException();

	public EmailSendFailException() {
		super(GlobalErrorCode.EMAIL_SEND_FAIL);
	}
}
