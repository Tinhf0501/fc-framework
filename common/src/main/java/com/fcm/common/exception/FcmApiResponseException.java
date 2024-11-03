package com.fcm.common.exception;

import com.fcm.common.response.ApiResponse;
import lombok.Getter;

@Getter
public class FcmApiResponseException extends RuntimeException {

    private final int httpStatus;
    private final ApiResponse<Object> apiResponse;


    public FcmApiResponseException(int httpStatus, ApiResponse<Object> apiResponse) {
        super(apiResponse.getCode() + " - " + apiResponse.getMessage());
        this.httpStatus = httpStatus;
        this.apiResponse = apiResponse;
    }
}
