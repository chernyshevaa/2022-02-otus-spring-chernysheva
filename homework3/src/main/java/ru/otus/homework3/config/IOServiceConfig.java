package ru.otus.homework3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework3.service.IOService;
import ru.otus.homework3.service.IOServiceStreams;

@Configuration
public class IOServiceConfig {
    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
