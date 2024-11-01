package com.fcm.openfeign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FcmFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FcmRequestInterceptor();
    }
}
