package com.fcm.common.exception;

import com.fcm.common.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getCode() + " - " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
