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
            put(1, new Question(1, "[3, 1, 4, 1, 5, ? ]", 9, "The value of PI"));
            put(2, new Question(2, "[1, 1, 2, 3, 5, ? ]", 8, "Fibonacci numbers"));
            put(3, new Question(3, "[1, 4, 9, 16, 25, ? ]", 36, "Square of natural numbers"));
            put(4, new Question(4, "[2, 3, 5, 7, 11, ? ]", 13, "Prime numbers"));
            put(5, new Question(5, "[1, 2, 4, 8, 16, ? ]", 32, "A doubling effect"));
            put(6, new Question(6, "[0, 1, 0, 1, 0, 1, ?]", 0, "Alternating"));
            put(7, new Question(7, "[6, 5, 4, 3, 2, 1, ?]", 0, "Backwards"));
        }
    };

    @Override
    public synchronized Optional<Question> getQuestionWithNumber(int number) {
        return Optional.ofNullable(map.get(number));
    }

}
