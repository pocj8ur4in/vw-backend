package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class EmailSendFailException extends BaseCodeException {
	public static final BaseCodeException baseCodeException = new EmailSendFailException();

	public EmailSendFailException() {
		super(GlobalErrorCode.EMAIL_SEND_FAIL);
	}
}
