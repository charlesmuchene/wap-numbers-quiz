package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;

import java.util.List;

/**
 * Question Dao contract
 */
public interface QuestionDAO {
    /**
     * Get all questions
     *
     * @return {@link List<Question>}
     */
    List<Question> getAllQuestions();

    /**
     * The number of questions available
     *
     * @return Number of questions available
     */
    int size();
}
