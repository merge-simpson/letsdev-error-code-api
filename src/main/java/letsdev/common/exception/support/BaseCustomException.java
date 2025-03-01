package letsdev.common.exception.support;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class BaseCustomException extends RuntimeException {

    protected final BaseErrorCode errorCode;
    protected final Runnable action;
    protected final Supplier<Map<String, Object>> payloadSupplier;

    public BaseCustomException() {
        super(getDefaultErrorCode().message());
        this.errorCode = getDefaultErrorCode();
        this.action = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(String message) {
        super(message);
        this.errorCode = getDefaultErrorCode();
        this.action = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = getDefaultErrorCode();
        this.action = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(BaseErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.action = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.action = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(BaseErrorCode errorCode, Runnable action) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.action = action;
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(BaseErrorCode errorCode, Runnable action, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.action = action;
        this.payloadSupplier = Collections::emptyMap;
    }

    public BaseCustomException(BaseErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.action = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    public BaseCustomException(
            BaseErrorCode errorCode,
            Supplier<Map<String, Object>> payloadSupplier,
            Throwable cause
    ) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.action = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    private static BaseErrorCode getDefaultErrorCode() {
        return DefaultBaseErrorCodeHolder.INSTANCE;
    }

    public BaseErrorCode getErrorCode() {
        return errorCode;
    }

    public void executeOnError() {
        action.run();
    }

    public Map<String, Object> getPayload() {
        return payloadSupplier.get();
    }

    public Map<String, Object> getPayloadOrElse(Map<String, Object> defaultPayload) {
        Objects.requireNonNull(defaultPayload, "The first argument `defaultPayload` must not be null");
        Map<String, Object> payload = payloadSupplier.get();
        return !payload.isEmpty() ? payload : defaultPayload;
    }

    public Map<String, Object> getPayloadOrElseGet(Supplier<Map<String, Object>> defaultPayloadSupplier) {
        Objects.requireNonNull(
                defaultPayloadSupplier,
                "The first argument `defaultPayloadSupplier` must not be null"
        );

        Map<String, Object> payload = payloadSupplier.get();
        return !payload.isEmpty() ? payload : defaultPayloadSupplier.get();
    }

    private static class DefaultBaseErrorCodeHolder { // 사용할 때 로드 + 스레드 세이프(클래스 로드 타임은 동시성 보장됨.)
        private static final BaseErrorCode INSTANCE = new BaseErrorCode() {
            @Override
            public String name() {
                return "SERVER_ERROR";
            }

            @Override
            public String message() {
                return "서버 오류";
            }

            @Override
            public int statusCode() {
                return 500;
            }

            @Override
            public BaseCustomException exception() {
                return new BaseCustomException(this);
            }

            @Override
            public BaseCustomException exception(Throwable cause) {
                return new BaseCustomException(this, cause);
            }

            @Override
            public BaseCustomException exception(Runnable action) {
                return new BaseCustomException(this, action);
            }

            @Override
            public BaseCustomException exception(Runnable action, Throwable cause) {
                return new BaseCustomException(this, action, cause);
            }

            @Override
            public BaseCustomException exception(Supplier<Map<String, Object>> payloadSupplier) {
                return new BaseCustomException(this, payloadSupplier);
            }

            @Override
            public BaseCustomException exception(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
                return new BaseCustomException(this, payloadSupplier, cause);
            }
        };
    }
}
