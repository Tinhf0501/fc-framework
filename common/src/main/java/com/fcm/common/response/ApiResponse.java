package com.fcm.common.response;

import com.fcm.common.constant.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private ApiBody apiBody;
    private ApiError apiError;
    private String code;
    private String message;
    private String traceId;
    private long duration;

    public static ApiResponse ok(ApiBody apiBody) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(CommonErrorCode.SUCCESS.getCode());
        apiResponse.setMessage(CommonErrorCode.SUCCESS.getMessage());
        apiResponse.setApiBody(apiBody);
        return apiResponse;
    }

    public static ApiResponse fail(IErrorCode code, ApiError apiError) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code.getCode());
        apiResponse.setMessage(code.getMessage());
        apiResponse.setApiError(apiError);
        return apiResponse;
    }

    public static ApiResponse fail(IErrorCode code) {
        ApiError apiError = new ApiError(code.getMessage(), null);
        return ApiResponse.fail(code, apiError);
    }

    public String getTraceId() {
        return MDC.get(AppConstants.TRACE_ID_KEY);
    }

    public long getDuration() {
        final long endTime = System.currentTimeMillis();
        final String startTime = MDC.get(AppConstants.START_TIME);
        return endTime - Long.parseLong(startTime);
    }
}