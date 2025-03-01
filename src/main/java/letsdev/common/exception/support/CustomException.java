package letsdev.common.exception.support;

import java.util.Map;
import java.util.function.Supplier;

public class CustomException extends BaseCustomException {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CustomException(ErrorCode errorCode, Runnable action) {
        super(errorCode, action);
    }

    public CustomException(ErrorCode errorCode, Runnable action, Throwable cause) {
        super(errorCode, action, cause);

    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);

    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }
}
