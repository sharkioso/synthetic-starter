package com.waylend.synthetic_starter.monitoring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.waylend.synthetic_starter.monitoring.sender.AuditMode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WeylandWatchingYou {
    AuditMode mode() default AuditMode.CONSOLE;
}
