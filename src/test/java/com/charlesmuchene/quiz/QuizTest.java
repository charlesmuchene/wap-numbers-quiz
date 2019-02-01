package com.charlesmuchene.quiz;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.models.Quiz;
import com.charlesmuchene.quiz.utilties.NoSuchQuestionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    private QuestionDAO questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new TestDAO();
    }

    @Test
    void shouldGetNextQuestion() throws NoSuchQuestionException {

        final Quiz quiz = new Quiz(questionDao);
        AtomicInteger currentQuestion = new AtomicInteger();

        Question nextQuestion = quiz.getNextQuestion(currentQuestion.get());

        assertEquals(1, nextQuestion.getNumber());

        nextQuestion = quiz.getNextQuestion(currentQuestion.incrementAndGet());

        assertEquals(2, nextQuestion.getNumber());

        assertThrows(NoSuchQuestionException.class, () -> {
            //noinspection InfiniteLoopStatement
            while (true) quiz.getNextQuestion(currentQuestion.incrementAndGet());
        });

    }
}