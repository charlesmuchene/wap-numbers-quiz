package com.charlesmuchene.quiz.models;

/**
 * Question model
 */
public class Question {

    private final int number;
    private final int answer;
    private final String questionText;

    /**
     * Question constructor
     *
     * @param number       Number of the question. Used as id in implementation.
     * @param questionText The text of the question.
     * @param answer       Answer to the question
     */
    public Question(int number, String questionText, int answer) {
        this.number = number;
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getAnswer() {
        return answer;
    }
}
