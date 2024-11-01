package com.fcm.common.response;

import com.fcm.common.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fcm.common.constant.Status;
import com.fcm.common.constant.ErrorCode;
import org.slf4j.MDC;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private ApiBody apiBody;
    private ApiError apiError;
    private ErrorCode code;
    private Status status;
    private String traceId;
    private long duration;

    public static ApiResponse ok(ApiBody apiBody) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Status.SUCCESS);
        apiResponse.setCode(ErrorCode.SUCCESS);
        apiResponse.setApiBody(apiBody);
        return apiResponse;
    }

    public static ApiResponse fail(ErrorCode code, ApiError apiError) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(Status.FAIL);
        apiResponse.setCode(code);
        apiResponse.setApiError(apiError);
        return apiResponse;
    }

    public static ApiResponse fail(ErrorCode code) {
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