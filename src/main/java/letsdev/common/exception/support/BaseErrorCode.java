package letsdev.common.exception.support;

/**
 * This interface is independent of any frameworks such as Spring.
 */
public interface BaseErrorCode {
    String name(); // automatically overridden in enum
    String defaultMessage();
    int statusCode();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
