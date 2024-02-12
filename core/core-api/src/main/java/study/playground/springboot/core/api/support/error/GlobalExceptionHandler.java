package study.playground.springboot.core.api.support.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.playground.springboot.core.api.support.error.exception.BusinessException;
import study.playground.springboot.core.api.support.error.exception.ErrorCode;
import study.playground.springboot.core.api.support.error.exception.ExternalException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     *  javax.validation 입력 값 검증 실패 시 발생
     *  @RequestBody or @RequestPart 에서 주로 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * javax.validation 입력 값 검증 실패 시 발생
     * 요청 파라미터 Binding 실패 시 발생
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 외부 API 오류로 인한 조회 실패 시 발생
     */
    @ExceptionHandler(ExternalException.class)
    protected ResponseEntity<ErrorResponse> handleExternalApiException(ExternalException e) {
        log.error("handleExternalException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.UNABLE_EXTERNAL_API_CALL_ERROR);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    /**
     * 비즈니스(도메인)의 규칙 위반 시 발생
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("handleBusinessException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * 핸들링 하지 못한 런타임 오류
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
        log.error("handleRuntimeException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 핸들링 하지 못한 오류
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
