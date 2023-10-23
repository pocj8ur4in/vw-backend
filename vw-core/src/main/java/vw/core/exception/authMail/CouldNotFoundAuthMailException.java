package vw.core.exception.authMail;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.GlobalErrorCode;

public class CouldNotFoundAuthMailException extends BaseCodeException { // 검색한 인증 이메일 정보가 존재하지 않는 경우
	public static final BaseCodeException baseCodeException = new CouldNotFoundAuthMailException();

	private CouldNotFoundAuthMailException() {
		super(GlobalErrorCode.COULD_NOT_FOUND_AUTH_MAIL);
	}
}
