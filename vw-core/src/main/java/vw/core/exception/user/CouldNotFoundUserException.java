package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class CouldNotFoundUserException extends BaseCodeException { // 검색한 회원 정보가 존재하지 않는 경우
	public static final BaseCodeException baseCodeException = new CouldNotFoundUserException();

	private CouldNotFoundUserException() {
		super(UserErrorCode.USER_NOT_FOUND);
	}
}
