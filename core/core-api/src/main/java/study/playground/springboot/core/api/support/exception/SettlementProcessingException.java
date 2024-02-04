package study.playground.springboot.core.api.support.exception;

import study.playground.springboot.core.api.support.error.exception.BusinessException;
import study.playground.springboot.core.api.support.error.exception.ErrorCode;

public class SettlementProcessingException extends BusinessException {

    public SettlementProcessingException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public SettlementProcessingException(ErrorCode errorCode) {
        super(errorCode);
    }
}