package letsdev.common.exception.support;

import java.util.Map;
import java.util.function.Supplier;

/**
 * This interface is independent of any frameworks such as Spring.
 */
public interface BaseErrorCode {
    String name(); // automatically overridden in enum
    String message();
    int statusCode();
    RuntimeException exception();
    RuntimeException exception(Throwable cause);
    RuntimeException exception(Runnable action);
    RuntimeException exception(Runnable action, Throwable cause);
    RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier);
    RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier, Throwable cause);
}
