package com.fcm.common.response;

import com.fcm.common.constant.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String code;
    private String message;
    private String traceId;
    private long duration;

    public static <T> ApiResponse<T> ok(T data, IMessage message) {
        final ApiResponse<T> apiResponse = ok(data);
        apiResponse.setMessage(message.getValue());
        return apiResponse;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CommonErrorCode.SUCCESS.getCode());
        apiResponse.setMessage(CommonErrorCode.SUCCESS.getMessage());
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> fail(IErrorCode code, Object...params) {
        if (Objects.isNull(params)) {
            params = new Object[]{};
        }
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code.getCode());
        apiResponse.setMessage(String.format(code.getMessage(), params));
        return apiResponse;
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