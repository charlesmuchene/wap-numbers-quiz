package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.controllers.QuizController;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.views.ConsoleView;

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

    private final QuizController controller = new QuizController(new ConsoleView(), new InMemoryData());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}