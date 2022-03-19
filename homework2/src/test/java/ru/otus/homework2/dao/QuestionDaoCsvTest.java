package ru.otus.homework2.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework2.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("QuestionDaoCsv test")
public class QuestionDaoCsvTest {
    @DisplayName("Question Dao should return questions")
    @Test
    public void shouldReturnQuestions() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("questions.csv");

        List<Question> questions = questionDao.getQuestions();

        assertEquals(5, questions.size());
    }

    @DisplayName("Question Dao should return empty questions list when there are no file")
    @Test
    public void shouldReturnEmptyQuestionsListWhenNoFile() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("noFile.csv");

        List<Question> questions = questionDao.getQuestions();

        assertNotNull(questions);
        assertEquals(0, questions.size());
    }

    @DisplayName("Question Dao should skip lines, that are wrong formatted")
    @Test
    public void shouldWrongFormatQuestionLine() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("questions_wrongFormat.csv");

        List<Question> questions = questionDao.getQuestions();

        assertNotNull(questions);
        assertEquals(1, questions.size());
    }
}
