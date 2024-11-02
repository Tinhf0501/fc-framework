package com.fcm.web.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    @Before("@annotation(org.springframework.web.bind.annotation.ExceptionHandler) && args(throwable,..)")
    public void logErrorException(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
    }
}