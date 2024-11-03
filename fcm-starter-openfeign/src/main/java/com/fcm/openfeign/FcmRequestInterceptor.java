package com.fcm.openfeign;

import com.fcm.common.constant.AppConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FcmRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String authorizationHeader = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
            }
        }
        requestTemplate.header(AppConstants.TRACE_ID_KEY, MDC.get(AppConstants.TRACE_ID_KEY));
    }
}
