package com.flux.spring.boot.telegram.mapping.annotation;

import com.flux.spring.boot.telegram.mapping.config.TelegramBotAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(TelegramBotAutoConfiguration.class)
public @interface EnableTelegramBot {
}
