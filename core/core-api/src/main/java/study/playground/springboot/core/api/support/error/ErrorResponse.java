package study.playground.springboot.core.api.support.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import study.playground.springboot.core.api.support.error.exception.ErrorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String detailMessage;

    private ErrorResponse(final ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code, final String message) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.code = code.getCode();
        this.detailMessage = message;
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult result) {
        return new ErrorResponse(code,  String.format("[%s] %s", result.getFieldError().getField(), result.getFieldError().getDefaultMessage()));
    }

    public static ErrorResponse of(final ErrorCode code, final String details) {
        return new ErrorResponse(code, details);
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }
}
