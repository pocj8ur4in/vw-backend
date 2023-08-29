package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class AlreadyEmailSendException extends BaseCodeException {
	public static final BaseCodeException baseCodeException = new AlreadyEmailSendException();

	public AlreadyEmailSendException() {
		super(GlobalErrorCode.ALREADY_EMAIL_SEND);
	}
}
