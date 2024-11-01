package com.fcm.openfeign;

import com.fcm.common.constant.AppConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

public class FcmRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AppConstants.TRACE_ID_KEY, MDC.get(AppConstants.TRACE_ID_KEY));
        requestTemplate.header("Authorization", ""); // todo: set access token
    }
}
