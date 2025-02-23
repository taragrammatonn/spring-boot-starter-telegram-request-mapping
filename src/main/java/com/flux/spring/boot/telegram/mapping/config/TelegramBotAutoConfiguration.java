package com.flux.spring.boot.telegram.mapping.config;

import com.flux.spring.boot.telegram.mapping.core.BotRequestProcessor;
import com.flux.spring.boot.telegram.mapping.core.ReactiveUpdateConsumer;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TelegramBotProperties.class)
public class TelegramBotAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public ReactiveUpdateConsumer reactiveUpdateConsumer(TelegramBotProperties properties, BotRequestProcessor botRequestProcessor) {
        return new ReactiveUpdateConsumer(properties.getToken(), botRequestProcessor);
    }

    @Bean
    @ConditionalOnMissingBean
    public BotRequestProcessor botRequestProcessor() {
        return new BotRequestProcessor();
    }
}
