package ru.otus.homework2.service;

import org.springframework.stereotype.Service;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;

import java.util.Collections;
import java.util.List;

@Service
public class UserInteractionServiceImpl implements UserInteractionService {
    private final IOService ioService;

    public UserInteractionServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void printMessage(String s) {
        ioService.outputString(s);
    }

    @Override
    public String askQuestion(String question) {
        printMessage(question);
        return ioService.readString();
    }

    @Override
    public Answer askQuestion(Question question) {
        printQuestion(question);
        Answer answer = getAnswer(question);
        printMessage("");
        return answer;
    }

    private void printQuestion(Question question) {
        printMessage(question.getText());

        List<Answer> answers = question.getAnswers();
        Collections.shuffle(answers);
        int answerNumber = 1;
        for (Answer answer : answers) {
            printMessage(answerNumber++ + ") " + answer.getText());
        }
    }

    private Answer getAnswer(Question question) {
        List<Answer> answers = question.getAnswers();

        int userAnswer = -1;
        boolean receivedAnswer = false;
        while (!receivedAnswer) {
            try {
                String userInput = ioService.readString();
                userAnswer = Integer.parseInt(userInput);
                if (userAnswer >= 1 && userAnswer <= answers.size()) {
                    receivedAnswer = true;
                } else {
                    printMessage("You should input the number of correct answer: ");
                }
            } catch (NumberFormatException exception) {
                printMessage("Your input should be a number: ");
            }
        }
        return answers.get(userAnswer - 1);
    }
}
