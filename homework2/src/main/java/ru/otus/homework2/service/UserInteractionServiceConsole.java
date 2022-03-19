package ru.otus.homework2.service;

import org.springframework.stereotype.Service;

@Service
public class UserInteractionServiceConsole extends UserInteractionServiceImpl {
    public UserInteractionServiceConsole() {
        super(System.out, System.in);
    }
}
