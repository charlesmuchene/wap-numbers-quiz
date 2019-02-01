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
    private View view;

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
     * Set the given view
     *
     * @param view {@link View} implementation
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Display the next question or quiz over
     *
     * @return {@code true} if there was a question to display
     * otherwise {@code false}
     */
    public boolean displayNextQuestion() {
        int nextQuestionNumber = state.getNextQuestionNumber();
        return displayQuestionWith(nextQuestionNumber);
    }

    /**
     * Display the current question or quiz over
     *
     * @return {@code true} if there was a question to display
     * otherwise {@code false}
     */
    public boolean displayCurrentQuestion() {
        int nextQuestionNumber = state.getCurrentQuestionNumber();
        return displayQuestionWith(nextQuestionNumber);
    }

    /**
     * Display the question with the given number
     *
     * @param number Question number
     * @return {@code true} if there was a question to display
     * * otherwise {@code false}
     */
    private boolean displayQuestionWith(int number) {
        int score = state.getScore();
        Optional<Question> question = getQuestion(number);
        if (question.isPresent()) {
            displayQuestion(score, question.get());
            return true;
        } else {
            view.questionsOver(score);
            return false;
        }
    }

    /**
     * Get the question with the given number
     *
     * @param number Question number to retrieve
     * @return {@code Optional} question
     */
    private Optional<Question> getQuestion(int number) {
        return dao.getQuestionWithNumber(number);
    }

    /**
     * Display the given question
     *
     * @param score    Running score
     * @param question Question to display
     */
    private void displayQuestion(int score, Question question) {
        view.displayText(question.getQuestionText(), score);
    }

    /**
     * Verify that the given answer is the correct answer of the question
     * with the given question number.
     *
     * @param answer Possible answer for the question
     */
    public void validateAnswer(int answer) {
        int currentQuestion = state.getCurrentQuestionNumber();
        Optional<Question> question = dao.getQuestionWithNumber(currentQuestion);
        boolean isCorrect = question.filter(query -> query.getAnswer() == answer).isPresent();
        if (isCorrect) {
            state.incrementScore();
        } else {
            String text = question.isPresent() ? question.get().getQuestionText() : "";
            view.displayIncorrectAnswer(text, state.getScore());
        }
    }

    /**
     * Reset application state
     */
    public void resetState() {
        state.reset();
    }
}
