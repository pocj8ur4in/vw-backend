package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class LoginFailureException extends BaseCodeException { // 로그인이 실패한 경우
    public static final BaseCodeException baseCodeException = new LoginFailureException();

    public LoginFailureException() {
        super(UserErrorCode.LOGIN_FAILURE);
    }
}
