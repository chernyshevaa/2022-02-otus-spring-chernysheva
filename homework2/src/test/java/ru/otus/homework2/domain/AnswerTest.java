package ru.otus.homework2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test Answer class")
public class AnswerTest {
    private static final String TEST_ANSWER_TEXT = "test";
    @DisplayName("Constructor test")
    @Test
    public void shouldHaveCorrectConstructor() {
        Answer answer = new Answer(TEST_ANSWER_TEXT, true);

        assertEquals(TEST_ANSWER_TEXT, answer.getText());
        assertEquals(true, answer.isCorrect());
    }
}
