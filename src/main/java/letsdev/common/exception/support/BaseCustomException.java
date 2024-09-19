package letsdev.common.exception.support;

public class BaseCustomException extends RuntimeException {

    private static BaseErrorCode getDefaultErrorCode() {
        return DefaultBaseErrorCodeHolder.DEFAULT_BASE_ERROR_CODE;
    }

    protected final BaseErrorCode errorCode;

    public BaseCustomException() {
        super(getDefaultErrorCode().defaultMessage());
        this.errorCode = getDefaultErrorCode();
    }

    public BaseCustomException(String message) {
        super(message);
        this.errorCode = getDefaultErrorCode();
    }

    public BaseCustomException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = getDefaultErrorCode();
    }

    public BaseCustomException(BaseErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.errorCode = errorCode;
    }

    public BaseCustomException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.errorCode = errorCode;
    }

    public BaseErrorCode getErrorCode() {
        return errorCode;
    }

    private static class DefaultBaseErrorCodeHolder { // 사용할 때 로드 + 스레드 세이프(클래스 로드 타임은 동시성 보장됨.)
        private static final BaseErrorCode DEFAULT_BASE_ERROR_CODE = new BaseErrorCode() {
            @Override
            public String name() {
                return "SERVER_ERROR";
            }

            @Override
            public String defaultMessage() {
                return "서버 오류";
            }

            @Override
            public int statusCode() {
                return 500;
            }

            @Override
            public BaseCustomException defaultException() {
                return new BaseCustomException(this);
            }

            @Override
            public BaseCustomException defaultException(Throwable cause) {
                return new BaseCustomException(this, cause);
            }
        };
    }
}
