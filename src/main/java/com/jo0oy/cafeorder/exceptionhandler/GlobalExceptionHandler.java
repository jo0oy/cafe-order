package com.jo0oy.cafeorder.exceptionhandler;

import com.jo0oy.cafeorder.global.api.Api;
import com.jo0oy.cafeorder.global.error.ErrorCode;
import com.jo0oy.cafeorder.global.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<?>> exception(
        Exception exception
    ) {
        log.error("", exception);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
              Api.ERROR(ErrorCode.SERVER_ERROR)
            );
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<?>> exception(
        ApiException exception
    ) {
        log.error("", exception);

        var errorCode = exception.getErrorCodeIfs();

        return ResponseEntity
            .status(errorCode.getHttpStatusCode())
            .body(
                Api.ERROR(errorCode, exception.getErrorDescription())
            );
    }
}
