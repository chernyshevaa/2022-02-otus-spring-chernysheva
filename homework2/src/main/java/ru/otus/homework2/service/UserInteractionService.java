package ru.otus.homework2.service;

import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;

public interface UserInteractionService {
    void printMessage(String s);

    String askQuestion(String question);

    Answer askQuestion(Question question);
}
