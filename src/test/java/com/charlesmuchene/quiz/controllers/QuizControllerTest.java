package com.charlesmuchene.quiz.controllers;

import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.views.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class QuizControllerTest {

    private QuizController controller;

    private View view = mock(View.class);
    private QuestionDAO dao = mock(QuestionDAO.class);
    private ApplicationState state = mock(ApplicationState.class);
    private final Question dummyQuestion = new Question(-1, "Is this the most dumb question", -1);

    @BeforeEach
    void setUp() {
        controller = new QuizController(view, dao, state);
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
        verify(view).questionsOver(anyInt());

    }

    @Test
    void shouldDisplayNextQuestion() {
        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.of(dummyQuestion));

        boolean isQuestionDisplayed = controller.displayNextQuestion();

        assertTrue(isQuestionDisplayed);
        verify(view).displayText(anyString(), anyInt());
    }

    @Test
    void answerIsCorrect() {
        when(dao.getQuestionWithNumber(anyInt())).thenReturn(Optional.of(dummyQuestion));

        int possibleAnswer = dummyQuestion.getAnswer();

        controller.validateAnswer(possibleAnswer);

        verify(view, times(0)).displayIncorrectAnswer(anyString(), anyInt());
    }

}