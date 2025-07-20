package com.waylend.synthetic_starter.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.waylend.synthetic_starter.command.srevice.CommandService;
import com.waylend.synthetic_starter.monitoring.Audit.AuditAspect;
import com.waylend.synthetic_starter.monitoring.Audit.sender.AuditSender;
import com.waylend.synthetic_starter.monitoring.Audit.sender.KafkaAuditSender;
import com.waylend.synthetic_starter.monitoring.metrics.MetricsAspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@AutoConfiguration
@ConditionalOnProperty(name = "weyland.audit.enabled", havingValue = "true", matchIfMissing = true)
public class SyntheticAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommandService commandService() {
        return new CommandService();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditAspect auditAspect(AuditSender sender) {
        return new AuditAspect(sender);
    }

    @Bean
    @ConditionalOnMissingBean
    public MeterRegistry meterRegistry()
    {
        return new SimpleMeterRegistry();
    }
    
    @Bean
    @ConditionalOnClass(name = "org.springframework.kafka.core.KafkaTemplate")
    @ConditionalOnProperty(name = "weyland.audit.mode", havingValue = "KAFKA")
    public AuditSender kafkaAuditSender(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaAuditSender(kafkaTemplate);
    }

    @Bean
    @ConditionalOnProperty(name = "weyland.audit.mode", havingValue = "CONSOLE", matchIfMissing = true)
    public MetricsAspect metricsAspect(MeterRegistry registry) {
        return new MetricsAspect(registry);
    }
}
