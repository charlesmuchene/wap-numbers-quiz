package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;

import java.util.Optional;

/**
 * Question Dao contract
 * <p>
 * NB: Implementations must be thread safe
 */
public interface QuestionDAO {

    /**
     * Get the question with the given number.
     * <p>
     * Implementations should have ensure this method is thread-safe.
     *
     * @param number Question number to retrieve
     * @return Requested {@link Question} as an {@link Optional}
     */
    Optional<Question> getQuestionWithNumber(int number);

    /**
     * The number of questions available
     *
     * @return Number of questions available
     */
    int size();

}
