package com.waylend.synthetic_starter.monitoring.sender;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsoleAuditSender implements AuditSender {

    @Override
    public void send(AuditMode mode, String methodName, Object[] args, Object result) {
        log.info("[AUDIT] Method:{},Args: {}, Result: {}", methodName, args, result);
    }

}
