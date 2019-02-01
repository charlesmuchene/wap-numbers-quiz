package com.charlesmuchene.quiz.utilties;

/**
 * A marker exception to denote no more questions available for asking
 * <p>
 * Implementations should catch this exception to get notified when
 * there is no such a question with the given number.
 */
public class NoSuchQuestionException extends Exception {

    /**
     * NoSuchQuestionException constructor
     */
    public NoSuchQuestionException() {
        super("No such question");
    }
}
