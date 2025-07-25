package com.waylend.synthetic_starter.monitoring.Audit.sender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(name = "weyland.audit.mode", havingValue = "KAFKA")
public class KafkaAuditSender implements AuditSender{

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void send(AuditMode mode, String methodName, Object[] args, Object result) {
        String message = String.format("Method: %s | Args: %s | Result: %s",methodName,args,result);
        kafkaTemplate.send("weyland-audit-topic",message);
    }

}
