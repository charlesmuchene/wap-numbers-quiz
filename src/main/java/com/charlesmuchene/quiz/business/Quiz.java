package com.charlesmuchene.quiz.business;

import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.presentation.Presentation;

import java.util.Optional;

/**
 * Quiz logic
 */
public class Quiz {

    private final ApplicationState state;
    private final QuestionDAO dao;
    private Presentation presentation;

    /**
     * Quiz constructor
     *
     * @param presentation {@link Presentation} implementation
     * @param questionDAO  {@link QuestionDAO} implementation
     * @param state        {@link ApplicationState} implementation
     */
    public Quiz(Presentation presentation, QuestionDAO questionDAO, ApplicationState state) {
        this.presentation = presentation;
        this.state = state;
        this.dao = questionDAO;
    }

    /**
     * Set the given presentation
     *
     * @param presentation {@link Presentation} implementation
     */
    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
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
     * @return {@code true} if there was a question to display otherwise {@code false}
     */
    private boolean displayQuestionWith(int number) {
        int score = state.getScore();
        Optional<Question> question = getQuestion(number);
        if (question.isPresent())
            presentation.displayQuestionText(question.get().getQuestionText(), score, question.get().getHint());
        else presentation.questionsOver(score);
        return question.isPresent();
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
     * Verify that the given answer is the correct answer of the question
     * with the given question number.
     *
     * @param answer Possible answer for the question
     * @return {@code true} if the answer is valid {@code false} otherwise
     */
    public boolean validateAnswer(int answer) {
        int currentQuestion = state.getCurrentQuestionNumber();
        Optional<Question> question = dao.getQuestionWithNumber(currentQuestion);
        boolean isCorrect = question.filter(query -> query.getAnswer() == answer).isPresent();
        if (isCorrect) state.incrementScore();
        return isCorrect;
    }

    /**
     * Reset application state
     */
    public void resetState() {
        state.reset();
    }

    /**
     * Sanitize user input
     * <p>
     * NB: Never trust the user input! :D
     *
     * @param input User input
     * @return {@link Optional} sanitized input
     */
    public Optional<Integer> sanitizeInput(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }

    /**
     * Display input as invalid
     */
    public void displayInvalidInput() {
        int questionNumber = state.getCurrentQuestionNumber();
        getQuestion(questionNumber).ifPresent((question) ->
                presentation.displayInputAsInvalid(question.getQuestionText(), state.getScore(), question.getHint()));
    }

    /**
     * Display incorrect answer
     */
    public void displayIncorrectAnswer() {
        int currentQuestionNumber = state.getCurrentQuestionNumber();
        Optional<Question> question = dao.getQuestionWithNumber(currentQuestionNumber);
        String text = question.isPresent() ? question.get().getQuestionText() : "";
        String hint = question.isPresent() ? question.get().getHint() : "";
        presentation.displayIncorrectAnswer(text, state.getScore(), hint);
    }
}
