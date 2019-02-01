package com.charlesmuchene.quiz;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.models.Quiz;
import com.charlesmuchene.quiz.utilties.NoMoreQuestionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    private QuestionDAO questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new TestDAO();
    }

    @Test
    void shouldGetNextQuestion() throws NoMoreQuestionsException {
        final Quiz quiz = new Quiz(questionDao);
        Question nextQuestion = quiz.getNextQuestion();

        assertEquals(1, nextQuestion.getNumber());

        nextQuestion = quiz.getNextQuestion();

        assertEquals(2, nextQuestion.getNumber());

        assertThrows(NoMoreQuestionsException.class, () -> {
            //noinspection InfiniteLoopStatement
            while (true) quiz.getNextQuestion();
        });

    }
}