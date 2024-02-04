package study.playground.springboot.core.api.support.exception;

import study.playground.springboot.core.api.support.error.exception.BusinessException;
import study.playground.springboot.core.api.support.error.exception.ErrorCode;

public class ExceedBalanceException extends BusinessException {

    public ExceedBalanceException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public ExceedBalanceException(ErrorCode errorCode) {
        super(errorCode);
    }
}