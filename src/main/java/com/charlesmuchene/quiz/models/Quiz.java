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
    private final int totalQuestions;

    /**
     * Quiz constructor
     *
     * @param questionDao {@link QuestionDAO} implementation
     */
    public Quiz(QuestionDAO questionDao) {
        this.questionDao = questionDao;
        this.totalQuestions = questionDao.size();
    }

    /**
     * Get the next question
     *
     * @param currentQuestion Current question number
     * @return Next {@link Question} to ask
     * @throws NoSuchQuestionException when there are no more questions to ask
     */
    public Question getNextQuestion(int currentQuestion) throws NoSuchQuestionException {
        int nextQuestion = currentQuestion + 1;
        if (nextQuestion < totalQuestions)
            return questionDao.getQuestionWithNumber(nextQuestion);
        throw new NoSuchQuestionException();
    }

}
