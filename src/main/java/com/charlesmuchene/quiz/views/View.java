package com.charlesmuchene.quiz.views;

/**
 * View contract
 */
public interface View {

    /**
     * Display the given text and score
     *
     * @param text  Question to display
     * @param score Score to display
     */
    void displayText(String text, String score);

    /**
     * Display no more questions
     *
     * @param score Final score
     */
    void questionsOver(String score);

}
