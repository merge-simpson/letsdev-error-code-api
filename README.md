# Features

## 확장 가능한 Error Code

- `<<interface>> ErrorCode` (Spring-dependent)
- `<<interface>> BaseErrorCode` (Spring-independent)

`ErrorCode`는 `BaseErrorCode`를 게승합니다. 이것들을 `enum` 클래스에 구현시킬 수 있습니다. (ex: `SignUpErrorCode` 등)

## 에러코드 핸들링 Custom Exception

- `<<class>> CustomException` (Spring-dependent)
- `<<class>> BaseCustomException` (Spring-independent)

`CustomException`은 `ErrorCode`를 핸들링하고, `BaseCustomException`은 `BaseErrorCode`를 핸들링합니다.  
하지만 `ErrorCode`가 `BaseErrorCode`를 계승하는 것과 달리, `CustomException`과 `BaseCustomException`은 독립적입니다.  

`CustomException`은 오직 `RuntimeException`을 상속받아 구현되었으며, `HttpStatusCodeException`에도 독립적입니다.
따라서 스프링에서 상태 코드를 변경하여 반환하는 로직은 별도로 구현하여야 합니다. 예를 들어 `@ControllerAdvice`나
`@RestControllerAdvice` 등에서 다음처럼 할 수 있습니다.

```java
@RestControllerAdvice
public final class GlobalExceptionHandler {
    // CustomException과 그것을 계승한 모든 예외 클래스를 캐치하여 핸들링합니다.
    @ExceptionHandler(CustomException.class)
    public ResponseEntity</* 반환 타입, json object 또는 map 등 */> handleCustomException(CustomException exception) {
        HttpStatus status = exception.defaultHttpStatus();
        // ...
        return ResponseEntity
                .status(status)
                .body(/* 반환할 응답 바디 */);
    }
    
}
```

# Spring-Dependent

Spring 프레임워크에 의존하는 클래스는 다음과 같습니다.

- `<<interface>> ErrorCode`
- `<<class>> CustomException`

이것들을 올바르게 사용한다면 최소한 다음 의존성을 포함해야 합니다.

|        Group        | Artifact ID |
|:-------------------:|:-----------:|
| org.springframework | spring-web  |
