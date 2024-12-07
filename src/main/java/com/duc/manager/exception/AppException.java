package com.duc.manager.exception;

import org.h2.api.ErrorCode;

public class AppException extends RuntimeException{


    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
