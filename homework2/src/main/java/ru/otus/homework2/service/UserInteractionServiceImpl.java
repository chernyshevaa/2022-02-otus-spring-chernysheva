package ru.otus.homework2.service;

import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserInteractionServiceImpl implements UserInteractionService {
    private final PrintStream printStream;
    private final Scanner inputScanner;

    public UserInteractionServiceImpl(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputScanner = new Scanner(inputStream);
    }

    @Override
    public void printMessage(String s) {
        printStream.println(s);
    }

    @Override
    public String askQuestion(String question) {
        inputScanner.reset();
        printMessage(question);
        return inputScanner.nextLine();
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
            inputScanner.reset();
            try {
                String userInput = inputScanner.next();
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
