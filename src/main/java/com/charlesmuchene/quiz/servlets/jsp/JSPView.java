package com.charlesmuchene.quiz.servlets.jsp;

import com.charlesmuchene.quiz.views.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSP {@link View} implementation
 */
public class JSPView implements View {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /**
     * JSPView constructor
     *
     * @param request  {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance
     */
    public JSPView(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void displayQuestionText(String text, int score) {

    }

    @Override
    public void questionsOver(int score) {

    }

    @Override
    public void displayIncorrectAnswer(String text, int score) {

    }

    @Override
    public void displayInputAsInvalid(String text, int score) {

    }
}
