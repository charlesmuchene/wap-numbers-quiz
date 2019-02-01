package com.charlesmuchene.quiz.models;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.utilties.NoMoreQuestionsException;

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
     * @return {@link Question}
     * @throws NoMoreQuestionsException when there are no more questions to ask
     */
    public Question getNextQuestion(int currentQuestion) throws NoMoreQuestionsException {
        currentQuestion++;
        if (currentQuestion < totalQuestions)
            return questionDao.getAllQuestions().get(currentQuestion);
        throw new NoMoreQuestionsException();
    }

}
