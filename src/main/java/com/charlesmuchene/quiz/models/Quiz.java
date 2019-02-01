package com.charlesmuchene.quiz.models;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.utilties.NoMoreQuestionsException;

public class Quiz {

    private final QuestionDAO questionDao;
    private int currentQuestion = -1;
    private final int totalQuestions;

    public Quiz(QuestionDAO questionDao) {
        this.questionDao = questionDao;
        this.totalQuestions = questionDao.size();
    }

    public Question getNextQuestion() throws NoMoreQuestionsException {
        currentQuestion++;
        if (currentQuestion < totalQuestions)
            return questionDao.getAllQuestions().get(currentQuestion);
        throw new NoMoreQuestionsException();
    }

}
