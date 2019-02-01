package com.charlesmuchene.quiz.models;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.utilties.NoSuchQuestionException;

/**
 * Quiz model
 * <p>
 * Gives access to questions
 */
public class Quiz {

    private final QuestionDAO questionDao;

    /**
     * Quiz constructor
     *
     * @param questionDao {@link QuestionDAO} implementation
     */
    public Quiz(QuestionDAO questionDao) {
        this.questionDao = questionDao;
    }

    /**
     * Get the next question
     *
     * @param number Question number to retrieve
     * @return Next {@link Question} to ask
     * @throws NoSuchQuestionException when there is no question with that number
     */
    public Question getNextQuestion(int number) throws NoSuchQuestionException {
        return questionDao.getQuestionWithNumber(number);
    }

}
