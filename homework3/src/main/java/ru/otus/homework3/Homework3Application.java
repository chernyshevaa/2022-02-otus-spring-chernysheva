package ru.otus.homework3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.homework3.service.TestingService;

import java.util.Locale;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Homework3Application.class, args);

        TestingService testingService = context.getBean(TestingService.class);
        testingService.performTesting(Locale.getDefault());
    }

}
