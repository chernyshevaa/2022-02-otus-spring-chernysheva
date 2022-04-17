package ru.otus.homework3.service;

import ru.otus.homework3.domain.Answer;
import ru.otus.homework3.domain.Question;

public interface UserInteractionService {
    void printMessage(String s);

    String askQuestion(String question);

    Answer askQuestion(Question question);
}
