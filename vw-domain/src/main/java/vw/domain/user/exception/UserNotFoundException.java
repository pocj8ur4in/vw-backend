package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class UserNotFoundException extends BaseCodeException { // 사용자 정보를 찾을 수 없는 경우
	public static final BaseCodeException baseCodeException = new UserNotFoundException();

	public UserNotFoundException() {
		super(UserErrorCode.USER_NOT_FOUND);
	}
}
