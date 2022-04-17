package ru.otus.homework3.dao;

import ru.otus.homework3.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDao {
    List<Question> getQuestions(Locale locale);
}
