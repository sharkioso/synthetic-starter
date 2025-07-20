package com.waylend.synthetic_starter.monitoring.Audit.sender;

public interface AuditSender {
    void send(AuditMode mode, String methodName, Object[] args, Object result);
}
