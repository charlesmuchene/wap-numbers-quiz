package com.charlesmuchene.quiz.models;

public class Question {

    private final int number;
    private final int answer;
    private final String questionText;

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
