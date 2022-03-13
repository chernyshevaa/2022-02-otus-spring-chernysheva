package ru.otus.homework1.service;

import ru.otus.homework1.dao.QuestionDao;
import ru.otus.homework1.domain.Answer;
import ru.otus.homework1.domain.Question;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class TestingServiceImpl implements TestingService {
    private final QuestionDao questionDao;
    private final PrintStream printStream;

    public TestingServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.printStream = System.out;
    }

    public void showQuestions() {
        List<Question> questions = questionDao.getQuestions();
        for (Question question : questions) {
            printStream.println(question.getText());
            List<Answer> answers = question.getAnswers();
            Collections.shuffle(answers);
            int answerNumber = 1;
            for (Answer answer : answers) {
                printStream.println(answerNumber++ + ") " + answer.getText());
            }
            printStream.println();
        }
    }
}
