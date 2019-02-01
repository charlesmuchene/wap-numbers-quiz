package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An in-memory implementation of the {@link QuestionDAO} with a naive
 * implementation of the required thread-safe {@link #getQuestionWithNumber(int)} method.
 */
public class InMemoryData implements QuestionDAO {

    private Map<Integer, Question> map = new ConcurrentHashMap<Integer, Question>() {
        {
            put(1, new Question(1, "[3, 1, 4, 1, 5, ? ]", 9));
            put(2, new Question(2, "[1, 1, 2, 3, 5, ? ]", 8));
            put(3, new Question(3, "[1, 4, 9, 16, 25, ? ]", 36));
            put(4, new Question(4, "[2, 3, 5, 7, 11, ? ]", 13));
            put(5, new Question(5, "[1, 2, 4, 8, 16, ? ]", 32));
        }
    };

    @Override
    public synchronized Optional<Question> getQuestionWithNumber(int number) {
        return Optional.ofNullable(map.get(number));
    }

}
