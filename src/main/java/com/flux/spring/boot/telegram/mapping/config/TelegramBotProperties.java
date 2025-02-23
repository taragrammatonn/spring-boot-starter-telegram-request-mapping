package com.flux.spring.boot.telegram.mapping.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotProperties {
    private String token;
}
