package letsdev.common.exception.support;

import org.springframework.http.HttpStatus;

/**
 * An error code interface depending on Spring Framework.
 */
public interface ErrorCode extends BaseErrorCode {
    HttpStatus defaultHttpStatus();

    default int statusCode() {
        assert defaultHttpStatus() != null : "ErrorCode must have a non-null http status.";
        return defaultHttpStatus().value();
    }
}
