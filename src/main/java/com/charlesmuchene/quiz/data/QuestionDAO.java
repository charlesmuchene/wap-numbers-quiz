package com.charlesmuchene.quiz.data;

import com.charlesmuchene.quiz.models.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getAllQuestions();
    int size();
}
