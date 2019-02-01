package com.charlesmuchene.quiz.views;

/**
 * View contract
 */
public interface View {

    /**
     * Display the given text and score
     *
     * @param text  Question to display
     * @param score Running score to display
     */
    void displayText(String text, int score);

    /**
     * Display no more questions
     *
     * @param score Final score
     */
    void questionsOver(int score);

    /**
     * Display incorrect answer
     *
     * @param text  Question to display
     * @param score Running score to display
     */
    void displayIncorrectAnswer(String text, int score);

}
