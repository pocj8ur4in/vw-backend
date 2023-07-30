package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class UserNotRegisteredException extends BaseCodeException { // 사용자의 회원가입이 실패한 경우
    public static final BaseCodeException baseCodeException = new UserNotRegisteredException();

    public UserNotRegisteredException() {
        super(UserErrorCode.USER_NOT_REGISTERED);
    }
}
