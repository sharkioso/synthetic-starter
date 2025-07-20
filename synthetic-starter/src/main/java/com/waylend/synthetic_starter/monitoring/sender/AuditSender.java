package com.waylend.synthetic_starter.monitoring.sender;

public interface AuditSender {
    void send(AuditMode mode, String methodName, Object[] args, Object result);
}
