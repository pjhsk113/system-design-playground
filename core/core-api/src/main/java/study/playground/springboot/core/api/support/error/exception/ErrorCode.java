package study.playground.springboot.core.api.support.error.exception;

public enum ErrorCode {

    /**
     * HTTP Method 오류 (405)
     */
    METHOD_NOT_ALLOWED(405, "E3001", "지원하지 않는 HTTP 메소드입니다. 요청 방식을 확인해주세요."),

    /**
     * 요청 입력 값 오류 (400)
     */
    INVALID_INPUT_VALUE(400, "E3002", "입력 값이 올바르지 않습니다."),

    /**
     * 외부 API 조회 오류
     */
    UNABLE_EXTERNAL_API_CALL_ERROR(406, "E3003", "요청에 성공했지만, 외부 API 콘텐츠 특성으로 응답할 수 없습니다."),

    /**
     * 서버 내부 오류 (500)
     */
    INTERNAL_SERVER_ERROR(500, "E3999", "서버에 알 수 없는 오류가 발생했습니다.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}