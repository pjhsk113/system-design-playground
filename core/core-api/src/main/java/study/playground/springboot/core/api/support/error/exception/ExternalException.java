package study.playground.springboot.core.api.support.error.exception;

public class ExternalException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExternalException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ExternalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
