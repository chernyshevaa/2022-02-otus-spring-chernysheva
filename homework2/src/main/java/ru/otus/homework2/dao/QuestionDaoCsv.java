package ru.otus.homework2.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class QuestionDaoCsv implements QuestionDao {
    private static final Logger logger = LogManager.getLogger(QuestionDaoCsv.class);

    private String fileName;

    public QuestionDaoCsv(@Value("${fileName}")String fileName) {
        this.fileName = fileName;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(getQuestionsInputStream())) {
            while (scanner.hasNextLine()) {
                questions.add(getQuestionFromLine(scanner.nextLine()));
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        return questions;
    }

    private InputStream getQuestionsInputStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            logger.error("File " + fileName + " is not found");
        }

        return inputStream;
    }

    private Question getQuestionFromLine(String line) throws Exception {
        String[] questionData = line.split(",");
        if (questionData.length < 3) {
            throw new Exception("Wrong format of question. Line " + line + " could not be transformed to question.");
        }

        String questionText = questionData[0];
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(questionData[1], true));

        for (int i = 2; i < questionData.length; i++) {
            answers.add(new Answer(questionData[i], false));
        }

        return new Question(questionText, answers);
    }
}
