package com.fcm.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fcm.common.exception.FcmApiResponseException;
import com.fcm.common.response.ApiResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class FcmErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder.Default errorDecoderDefault = new Default();
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String feignMethodName, Response response) {
        try {
            ApiResponse<Object> apiResponse = this.objectMapper.readValue(response.body().asInputStream(), ApiResponse.class);
            if (Objects.isNull(apiResponse)) {
                throw new RuntimeException();
            }
            return new FcmApiResponseException(response.status(), apiResponse);
        } catch (Exception ex) {
            return this.errorDecoderDefault.decode(feignMethodName, response);
        }
    }
}
