package study.playground.springboot.core.api.support.exception;

import study.playground.springboot.core.api.support.error.exception.BusinessException;
import study.playground.springboot.core.api.support.error.exception.ErrorCode;

public class UserStateException extends BusinessException {

    public UserStateException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public UserStateException(ErrorCode errorCode) {
        super(errorCode);
    }
}