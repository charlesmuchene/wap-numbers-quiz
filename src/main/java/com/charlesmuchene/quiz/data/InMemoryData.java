package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in-memory implementation of the {@link QuestionDAO}
 */
public class InMemoryData implements QuestionDAO {

    private Map<Integer, Question> map = new HashMap<Integer, Question>() {
        {
            put(1, new Question(1, "[3, 1, 4, 1, 5, ? ]", 9));
            put(2, new Question(2, "[1, 1, 2, 3, 5, ? ]", 8));
        }
    };

    public List<Question> getAllQuestions() {
        return null;
    }

    public int size() {
        return map.size();
    }
}
