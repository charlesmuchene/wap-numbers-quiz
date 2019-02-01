package com.charlesmuchene.quiz.data;

/**
 * Application state
 * <p>
 * Stores the current question and score.
 */
public class ApplicationState {

    private int currentQuestion = 0;
    private int score = 0;

    public int getNextQuestionNumber() {
        return ++currentQuestion;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestion == 0 ? 1 : currentQuestion;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public void reset() {
        currentQuestion = 1;
        score = 0;
    }
}
