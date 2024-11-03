package com.fcm.web.advice;

import com.fcm.common.constant.CommonErrorCode;
import com.fcm.common.exception.BusinessException;
import com.fcm.common.exception.FcmApiResponseException;
import com.fcm.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleAllException(Exception ex) {
        return ApiResponse.fail(CommonErrorCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleBindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        final String msg = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        return ApiResponse.fail(CommonErrorCode.VALIDATE_FAIL, msg);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(ApiResponse.fail(ex.getErrorCode(), ex.getParams()));
    }

    @ExceptionHandler(FcmApiResponseException.class)
    public ResponseEntity<ApiResponse<Object>> handleFcmApiResponseException(FcmApiResponseException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ex.getApiResponse());
    }
}
