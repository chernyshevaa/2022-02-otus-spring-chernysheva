package ru.otus.homework3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework3.config.TestingConfig;
import ru.otus.homework3.dao.QuestionDao;
import ru.otus.homework3.domain.Answer;
import ru.otus.homework3.domain.Question;
import ru.otus.homework3.util.InternationalizationUtils;

import java.util.List;
import java.util.Locale;

@Service
public class TestingServiceImpl implements TestingService {
    private final QuestionDao questionDao;
    private final UserInteractionService userInteractionService;
    private final int scoreToPassTest;

    private final InternationalizationUtils internationalizationUtils;

    public TestingServiceImpl(QuestionDao questionDao, UserInteractionService userInteractionService, TestingConfig testingConfig, InternationalizationUtils internationalizationUtils) {
        this.questionDao = questionDao;
        this.userInteractionService = userInteractionService;
        this.scoreToPassTest = testingConfig.getScoreToPass();
        this.internationalizationUtils = internationalizationUtils;
    }

    public void performTesting(Locale locale) {
        List<Question> questions = questionDao.getQuestions(locale);
        if (questions.size() == 0) {
            userInteractionService.printMessage(internationalizationUtils.getMessage("error.questions", locale));
            return;
        }

        String name = userInteractionService.askQuestion(internationalizationUtils.getMessage("enter.name", locale));

        int rightAnswersNumber = askQuestions(questions);

        showResult(rightAnswersNumber, questions.size(), name, locale);
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

    private void showResult(int rightAnswersNumber, int questionsNumber, String name, Locale locale) {
        userInteractionService.printMessage(internationalizationUtils.getMessage("test.score", locale, String.valueOf(rightAnswersNumber), String.valueOf(questionsNumber)));
        if (rightAnswersNumber >= scoreToPassTest) {
            userInteractionService.printMessage(internationalizationUtils.getMessage("test.passed", locale, name));
        } else {
            userInteractionService.printMessage(internationalizationUtils.getMessage("test.failed", locale, name));
        }
    }
}
