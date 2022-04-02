package ru.otus.homework2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework2.dao.QuestionDao;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final QuestionDao questionDao;
    private final UserInteractionService userInteractionService;
    private final int scoreToPassTest;

    public TestingServiceImpl(QuestionDao questionDao, UserInteractionService userInteractionService, @Value("${scoreToPassTest}") int scoreToPass) {
        this.questionDao = questionDao;
        this.userInteractionService = userInteractionService;
        this.scoreToPassTest = scoreToPass;
    }

    public void performTesting() {
        List<Question> questions = questionDao.getQuestions();
        if (questions.size() == 0) {
            userInteractionService.printMessage("There are problems getting list of questions");
            return;
        }

        String name = userInteractionService.askQuestion("Enter your name:");

        int rightAnswersNumber = askQuestions(questions);

        showResult(rightAnswersNumber, questions.size(), name);
    }

    private int askQuestions(List<Question> questions) {
        int rightAnswersCounter = 0;
        for (Question question : questions) {
            Answer userAnswer = userInteractionService.askQuestion(question);
            if (userAnswer == question.getCorrectAnswer()) {
                rightAnswersCounter++;
            }
        }
        return rightAnswersCounter;
    }

    private void showResult(int rightAnswersNumber, int questionsNumber, String name) {
        userInteractionService.printMessage("Your score: " + rightAnswersNumber + "/" + questionsNumber);
        if (rightAnswersNumber >= scoreToPassTest) {
            userInteractionService.printMessage(name + ", congratulations! You have passed the test.");
        } else {
            userInteractionService.printMessage(name + ", you have failed the test. Try again later.");
        }
    }
}
