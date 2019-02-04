package com.charlesmuchene.quiz.servlets.jsp;

import com.charlesmuchene.quiz.presentation.Presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JSP {@link Presentation} implementation
 */
public class JSPPresentation implements Presentation {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /**
     * JSPPresentation constructor
     *
     * @param request  {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance
     */
    public JSPPresentation(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void displayQuestionText(String text, int score) {
        forwardRequest(RequestState.DISPLAY, text, score);
    }

    @Override
    public void questionsOver(int score) {
        forwardRequest(RequestState.OVER, "", score);
    }

    @Override
    public void displayIncorrectAnswer(String text, int score) {
        forwardRequest(RequestState.INCORRECT, text, score);
    }

    @Override
    public void displayInputAsInvalid(String text, int score) {
        forwardRequest(RequestState.INVALID, text, score);
    }

    /**
     * Forward request to jsp
     *
     * @param state {@link Presentation.RequestState} instance
     * @param text  Question text
     * @param score Score
     */
    private void forwardRequest(RequestState state, String text, int score) {

        request.setAttribute("incorrect", state == RequestState.INCORRECT);
        request.setAttribute("invalid", state == RequestState.INVALID);
        request.setAttribute("over", state == RequestState.OVER);
        request.setAttribute("text", text);
        request.setAttribute("score", score);

        try {
            request.getRequestDispatcher("quiz.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
