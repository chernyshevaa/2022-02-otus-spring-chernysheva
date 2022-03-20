package ru.otus.homework2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework2.service.IOService;
import ru.otus.homework2.service.IOServiceStreams;

@Configuration
public class IOServiceConfig {
    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
