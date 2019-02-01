package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.controllers.QuizController;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Quiz Servlet
 */
@WebServlet(name = "Quiz", urlPatterns = "/quiz")
public class QuizServlet extends HttpServlet {

    private static final String SAME_REQUEST = "same_request";
    private QuizController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        QuestionDAO dao = new InMemoryData();
        ApplicationState state = new ApplicationState();
        controller = new QuizController(null, dao, state);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute(SAME_REQUEST, true);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object sameRequest = request.getAttribute(SAME_REQUEST);
        controller.setView(new ServletView(response.getWriter()));
        boolean nextQuestion = sameRequest == null? controller.displayCurrentQuestion() : controller.displayNextQuestion();
        if (!nextQuestion) {
            controller.resetState();
            System.out.println("Done with the questions");
        }
    }
}
