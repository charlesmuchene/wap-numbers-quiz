package com.charlesmuchene.quiz.utilties;

public class NoMoreQuestionsException extends Exception {
    public NoMoreQuestionsException() {
        super("No more questions");
    }
}
