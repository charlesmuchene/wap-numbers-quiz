package com.charlesmuchene.quiz.controllers;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.views.View;

import java.util.Optional;

/**
 * Quiz Controller
 */
public class QuizController {

    private final QuestionDAO dao;
    private final View view;

    public QuizController(View view, QuestionDAO questionDAO) {
        this.view = view;
        this.dao = questionDAO;
    }

    /**
     * Display the next question or quiz over
     *
     * @param number Question number to retrieve
     * @param score  Current score
     */
    public void displayNextQuestion(int number, String score) {
        Optional<Question> question = dao.getQuestionWithNumber(number);
        if (question.isPresent()) {
            view.displayText(question.get().getQuestionText(), score);
        } else {
            view.questionsOver(score);
        }
    }

    /**
     * Verify that the given answer is the correct answer of the question
     * with the given question number.
     *
     * @param questionNumber Question number to retrieve question
     * @param answer         Possible answer for the question
     * @return {@code true} if the answer is correct otherwise {@code false}
     */
    public boolean isCorrectAnswer(int questionNumber, int answer) {
        Optional<Question> question = dao.getQuestionWithNumber(questionNumber);
        return question.filter(query -> query.getAnswer() == answer).isPresent();
    }
}
