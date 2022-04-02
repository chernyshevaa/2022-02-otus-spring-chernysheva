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
import static org.mockito.Mockito.when;

@DisplayName("UserInteractionServiceImpl Test")
@ExtendWith(MockitoExtension.class)
public class UserInteractionServiceImplTest {
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    @Mock
    private IOService ioService;

    @DisplayName("Print message test")
    @Test
    public void shouldPrintMessage() {
        UserInteractionServiceImpl userInteractionService = new UserInteractionServiceImpl(ioService);

        userInteractionService.printMessage(QUESTION);

        verify(ioService).outputString(QUESTION);
    }

    @DisplayName("Ask string question test")
    @Test
    public void shouldAskQuestionString() {
        UserInteractionServiceImpl userInteractionService = new UserInteractionServiceImpl(ioService);
        when(ioService.readString()).thenReturn(ANSWER);

        String answer = userInteractionService.askQuestion(QUESTION);

        verify(ioService).outputString(QUESTION);
        assertEquals(ANSWER, answer);
    }
}
