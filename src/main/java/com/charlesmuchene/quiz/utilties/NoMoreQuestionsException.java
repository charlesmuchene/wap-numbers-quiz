package com.charlesmuchene.quiz.utilties;

/**
 * A marker exception to denote no more questions available for asking
 * <p>
 * Implementations should catch this exception to get notified when
 * there are no more questions to ask.
 */
public class NoMoreQuestionsException extends Exception {

    /**
     * NoMoreQuestionsException constructor
     */
    public NoMoreQuestionsException() {
        super("No more questions to ask");
    }
}
