package com.fcm.common.exception;

import com.fcm.common.constant.IErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private IErrorCode errorCode;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(IErrorCode errorCode) {
        this(errorCode.getCode() + " - " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
