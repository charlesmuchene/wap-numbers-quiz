package com.charlesmuchene.quiz.business;

import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.presentation.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class QuizTest {

    private Quiz controller;

    private Presentation presentation = mock(Presentation.class);
    private QuestionDAO dao = mock(QuestionDAO.class);
    private ApplicationState state = mock(ApplicationState.class);
    private final Question dummyQuestion = new Question(-1, "Is this the most dumb question", -1);

    @BeforeEach
    void setUp() {
        controller = new Quiz(presentation, dao, state);
    }

    @Test
    void gettingNextQuestionInvokesDao() {

        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.of(dummyQuestion));

        boolean isQuestionDisplayed = controller.displayNextQuestion();

        assertTrue(isQuestionDisplayed);
        verify(dao).getQuestionWithNumber(anyInt());

    }

    @Test
    void shouldDisplayQuestionsDone() {

        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.empty());

        boolean isQuestionDisplayed = controller.displayNextQuestion();

        assertFalse(isQuestionDisplayed);
        verify(presentation).questionsOver(anyInt());

    }

    @Test
    void shouldDisplayNextQuestion() {
        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.of(dummyQuestion));

        boolean isQuestionDisplayed = controller.displayNextQuestion();

        assertTrue(isQuestionDisplayed);
        verify(presentation).displayQuestionText(anyString(), anyInt());
    }

    @Test
    void answerIsCorrect() {
        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.of(dummyQuestion));

        int possibleAnswer = dummyQuestion.getAnswer();

        controller.validateAnswer(possibleAnswer);

        verify(presentation, times(0)).displayIncorrectAnswer(anyString(), anyInt());
    }

    @Test
    void sanitizeUserInput() {
        String input = "invalid";

        Optional<Integer> sanitizedInput = controller.sanitizeInput(input);

        assertFalse(sanitizedInput.isPresent());

        input = "10";

        sanitizedInput = controller.sanitizeInput(input);

        assertTrue(sanitizedInput.isPresent());
    }
}