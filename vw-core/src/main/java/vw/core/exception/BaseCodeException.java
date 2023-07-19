package vw.core.exception;

import vw.core.dto.BaseErrorReason;

public class BaseCodeException extends RuntimeException { // 실행 시 발생하는 예외를 나타냄 (RuntimeException 상속)
    private BaseErrorCode baseErrorCode; // 예외 발생 시에 해당 예외의 원인을 BaseErrorCode 객체에 저장

    public BaseErrorReason getErrorReason() { // 해당 예외의 원인을 반환
        return this.baseErrorCode.getErrorReason();
    }
}
