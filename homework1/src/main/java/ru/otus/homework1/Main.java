package ru.otus.homework1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework1.service.TestingService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingService testingService = context.getBean(TestingService.class);
        testingService.showQuestions();
    }
}
