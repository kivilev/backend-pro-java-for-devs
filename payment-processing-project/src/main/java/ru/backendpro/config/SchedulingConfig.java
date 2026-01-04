package ru.backendpro.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(
        prefix = "app.scheduling",
        name = "enabled",
        havingValue = "true"
)
public class SchedulingConfig {
}