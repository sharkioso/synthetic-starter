package com.waylend.synthetic_starter.monitoring.Audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.waylend.synthetic_starter.monitoring.Audit.sender.AuditSender;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditSender auditSender;

    @Around("@annotation(WeylandWathcingYou)")
    public Object auditMethod(ProceedingJoinPoint pjp, WeylandWatchingYou audit) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        try {
            Object result = pjp.proceed();
            auditSender.send(audit.mode(), methodName, args, result);
            return result;
        } catch (Exception e) {
            auditSender.send(audit.mode(), methodName, args, "Error" + e.getMessage());
            throw e;
        }
    }
}
