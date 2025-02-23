package com.flux.spring.boot.telegram.mapping.core;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReactiveUpdateConsumer implements LongPollingUpdateConsumer, SpringLongPollingBot {

    private final String botToken;
    private final BotRequestProcessor botRequestProcessor;
    private final Sinks.Many<Update> updateSink = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public void consume(List<Update> updates) {
        updates.forEach(update -> {
            log.debug("📩 Received a message: {}", update);
            updateSink.tryEmitNext(update);
        });
    }

    public Flux<Update> getUpdatesStream() {
        return updateSink.asFlux();
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @PostConstruct
    public void listenForUpdates() {
        getUpdatesStream().subscribe(botRequestProcessor::handleUpdate);
    }
}
