package vw.domain.user.exception;

import vw.core.exception.BaseCodeException;

public class UserNotFoundException extends BaseCodeException { // 유저 정보를 찾을 수 없는 오류
    public static final BaseCodeException baseCodeException = new UserNotFoundException();

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
