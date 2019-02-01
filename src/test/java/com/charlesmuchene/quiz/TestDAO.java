package com.charlesmuchene.quiz;

import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.models.Question;
import com.charlesmuchene.quiz.utilties.NoSuchQuestionException;

import java.util.*;

public class TestDAO implements QuestionDAO {

    private Map<Integer, Question> map = new HashMap<Integer, Question>() {
        {
            put(1, new Question(1, "[3, 1, 4, 1, 5, ? ]", 9));
            put(2, new Question(2, "[1, 1, 2, 3, 5, ? ]", 8));
            put(3, new Question(3, "[1, 1, 2, 3, 5, ? ]", 8));
        }
    };

    @Override
    public Question getQuestionWithNumber(int number) throws NoSuchQuestionException {
        if (number >= map.size()) throw new NoSuchQuestionException();
        return map.get(number);
    }

    @Override
    public int size() {
        return map.size();
    }
}
