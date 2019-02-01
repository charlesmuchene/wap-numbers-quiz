package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.utilties.NoSuchQuestionException;

/**
 * Question Dao contract
 */
public interface QuestionDAO {

    /**
     * Get the question with the given number
     *
     * @param number Question number to retrieve
     * @return Requested {@link Question}
     * @throws NoSuchQuestionException if the question doesn't exist
     */
    Question getQuestionWithNumber(int number) throws NoSuchQuestionException;

    /**
     * The number of questions available
     *
     * @return Number of questions available
     */
    int size();
}
