package letsdev.common.exception.support;

import org.springframework.http.HttpStatus;

/**
 * An error code interface depending on Spring Framework.
 */
public interface ErrorCode extends BaseErrorCode {
    HttpStatus httpStatus();

    default int statusCode() {
        assert httpStatus() != null : "ErrorCode must have a non-null http status.";
        return httpStatus().value();
    }
}
