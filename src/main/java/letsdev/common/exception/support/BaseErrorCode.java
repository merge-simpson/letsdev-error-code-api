package letsdev.common.exception.support;

/**
 * Independent on Spring Framework or etc.
 */
public interface BaseErrorCode {
    String name(); // automatically overridden in enum
    String defaultMessage();
    int statusCode();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
