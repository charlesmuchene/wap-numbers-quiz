package com.charlesmuchene.quiz.data;

/**
 * Application state
 * <p>
 * Stores the current question and score.
 */
public class ApplicationState {

    private int currentQuestion = 1;
    private int score = 0;

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setNextQuestion() {
        this.currentQuestion++;
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
