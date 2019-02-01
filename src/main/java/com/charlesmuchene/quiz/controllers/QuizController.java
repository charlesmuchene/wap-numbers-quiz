package com.charlesmuchene.quiz.controllers;

import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.views.View;

import java.util.Optional;

/**
 * Quiz Controller
 */
public class QuizController {

    private final ApplicationState state;
    private final QuestionDAO dao;
    private final View view;

    /**
     * QuizController constructor
     *
     * @param view        {@link View} implementation
     * @param questionDAO {@link QuestionDAO} implementation
     * @param state       {@link ApplicationState} implementation
     */
    public QuizController(View view, QuestionDAO questionDAO, ApplicationState state) {
        this.view = view;
        this.state = state;
        this.dao = questionDAO;
    }

    /**
     * Display the next question or quiz over
     *
     * @return {@code true} if there was a question to display
     * otherwise {@code false}
     */
    public boolean displayNextQuestion() {
        int score = state.getScore();
        int currentQuestion = state.getCurrentQuestion();
        Optional<Question> question = dao.getQuestionWithNumber(currentQuestion);
        if (question.isPresent()) view.displayText(question.get().getQuestionText(), score);
        else view.questionsOver(score);
        return question.isPresent();
    }

    /**
     * Verify that the given answer is the correct answer of the question
     * with the given question number.
     *
     * @param answer Possible answer for the question
     */
    public void validateAnswer(int answer) {
        int currentQuestion = state.getCurrentQuestion();
        Optional<Question> question = dao.getQuestionWithNumber(currentQuestion);
        boolean isCorrect = question.filter(query -> query.getAnswer() == answer).isPresent();
        if (isCorrect) state.incrementScore();
        else view.displayIncorrectAnswer();
        state.setNextQuestion();
    }

    /**
     * Reset application state
     */
    public void resetState() {
        state.reset();
    }
}
