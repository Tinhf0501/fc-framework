package com.fcm.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class FcmFeignConfiguration {

    @Setter(onMethod_ = { @Autowired })
    private ObjectMapper objectMapper;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FcmRequestInterceptor();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FcmErrorDecoder(this.objectMapper);
    }
}
