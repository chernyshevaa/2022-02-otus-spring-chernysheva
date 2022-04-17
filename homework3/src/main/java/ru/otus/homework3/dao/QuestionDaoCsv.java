package ru.otus.homework3.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework3.config.QuestionsConfig;
import ru.otus.homework3.domain.Answer;
import ru.otus.homework3.domain.Question;
import ru.otus.homework3.util.InternationalizationUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class QuestionDaoCsv implements QuestionDao {
    private static final Logger logger = LoggerFactory.getLogger(QuestionDaoCsv.class);

    private final String fileName;

    private final InternationalizationUtils internationalizationUtils;

    public QuestionDaoCsv(QuestionsConfig questionsConfig, InternationalizationUtils internationalizationUtils) {
        this.fileName = questionsConfig.getFileName();
        this.internationalizationUtils = internationalizationUtils;
    }

    public List<Question> getQuestions(Locale locale) {
        List<Question> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(getQuestionsInputStream(locale))) {
            while (scanner.hasNextLine()) {
                questions.add(getQuestionFromLine(scanner.nextLine()));
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

        return questions;
    }

    private InputStream getQuestionsInputStream(Locale locale) {
        ClassLoader classLoader = getClass().getClassLoader();
        String localizedFileName = internationalizationUtils.getLocalizedFileName(fileName, locale);
        InputStream inputStream = classLoader.getResourceAsStream(localizedFileName);

        if (inputStream == null) {
            logger.error("File " + localizedFileName + " is not found");
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
