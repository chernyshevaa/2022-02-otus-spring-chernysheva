package ru.otus.homework2.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@DisplayName("UserInteractionServiceImpl Test")
@ExtendWith(MockitoExtension.class)
public class UserInteractionServiceImplTest {
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    @Mock
    PrintStream printStream;

    @Mock
    InputStream inputStream;

    @DisplayName("Print message test")
    @Test
    public void shouldPrintMessage() {
        UserInteractionServiceImpl userInteractionService = new UserInteractionServiceImpl(printStream, inputStream);

        userInteractionService.printMessage(QUESTION);

        verify(printStream).println(QUESTION);
    }

    @DisplayName("Ask string question test")
    @Test
    public void shouldAskQuestionString() {
        InputStream answerInputStream = new ByteArrayInputStream(ANSWER.getBytes());
        UserInteractionServiceImpl userInteractionService = new UserInteractionServiceImpl(printStream, answerInputStream);

        String answer = userInteractionService.askQuestion(QUESTION);

        verify(printStream).println(QUESTION);
        assertEquals(ANSWER, answer);
    }
}
