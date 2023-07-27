package vw.core.exception.jwt;

import vw.core.exception.BaseCodeException;
import vw.core.exception.GlobalErrorCode;

public class ExpiredTokenException extends BaseCodeException { // 토큰의 인증 시간이 만료된 경우
    public static final BaseCodeException baseCodeException = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalErrorCode.TOKEN_EXPIRED);
    }
}
