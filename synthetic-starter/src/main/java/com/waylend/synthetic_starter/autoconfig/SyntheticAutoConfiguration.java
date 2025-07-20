package com.waylend.synthetic_starter.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.waylend.synthetic_starter.command.srevice.CommandService;
import com.waylend.synthetic_starter.monitoring.Audit.AuditAspect;
import com.waylend.synthetic_starter.monitoring.Audit.sender.AuditSender;
import com.waylend.synthetic_starter.monitoring.metrics.MetricsAspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@AutoConfiguration
public class SyntheticAutoConfiguration {

    @Bean
    public CommandService commandService() {
        return new CommandService();
    }

    @Bean
    public AuditAspect auditAspect(AuditSender sender) {
        return new AuditAspect(sender);
    }

    @Bean
    public MeterRegistry meterRegistry()
    {
        return new SimpleMeterRegistry();
    }

    @Bean
    public MetricsAspect metricsAspect(MeterRegistry registry) {
        return new MetricsAspect(registry);
    }
}
