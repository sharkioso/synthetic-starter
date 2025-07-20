package com.waylend.synthetic_starter.monitoring.metrics;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class MetricsAspect {

    private final MeterRegistry registry;

    @Around("@annotation(WithMetrics)")
    public Object measureMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        long startTime = System.currentTimeMillis();

        try {
            Object result = pjp.proceed();
            Timer.builder("method.execution.time")
                    .tags("method", methodName)
                    .register(registry)
                    .record(System.currentTimeMillis() - startTime, TimeUnit.MICROSECONDS);
            return result;
        } catch (Exception e) {
            Counter.builder("method.errors")
                    .tags("method", methodName)
                    .register(registry)
                    .increment();

            throw e;
        }

    }
}
