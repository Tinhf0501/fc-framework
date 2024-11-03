package com.fcm.common.exception;

import com.fcm.common.constant.IErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private IErrorCode errorCode;
    private Object[] params;

    public BusinessException(String msg) {
        super(msg);
        this.errorCode = null;
        this.params = new Object[]{};
    }

    public BusinessException(IErrorCode errorCode, Object...params) {
        this(errorCode.getCode() + " - " + String.format(errorCode.getMessage(), params));
        this.errorCode = errorCode;
        this.params = params;
    }
}
