package com.jo0oy.cafeorder.global.exception;

import com.jo0oy.cafeorder.global.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
