package com.waylend.synthetic_starter.monitoring.Audit.sender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Primary
@ConditionalOnProperty(
    name = "weyland.audit.mode", 
    havingValue = "CONSOLE", 
    matchIfMissing = true
)
public class ConsoleAuditSender implements AuditSender {

    @Override
    public void send(AuditMode mode, String methodName, Object[] args, Object result) {
        log.info("[AUDIT] Method:{},Args: {}, Result: {}", methodName, args, result);
    }

}
