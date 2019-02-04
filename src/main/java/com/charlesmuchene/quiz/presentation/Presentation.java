package com.charlesmuchene.quiz.presentation;

/**
 * Presentation contract
 */
public interface Presentation {

    /**
     * Request state enumeration
     */
    enum RequestState {
        DISPLAY,
        OVER,
        INCORRECT,
        INVALID
    }

    /**
     * Display the given question text and score
     *  @param text  Question to display
     * @param score Running score to display
     * @param hint
     */
    void displayQuestionText(String text, int score, String hint);

    /**
     * Display no more questions
     *
     * @param score Final score
     */
    void questionsOver(int score);

    /**
     * Display incorrect answer
     *  @param text  Question to display
     * @param score Running score to display
     * @param hint
     */
    void displayIncorrectAnswer(String text, int score, String hint);

    /**
     * Display that input is invalid
     *  @param text  Question to display
     * @param score Running score to display
     * @param hint
     */
    void displayInputAsInvalid(String text, int score, String hint);

}
