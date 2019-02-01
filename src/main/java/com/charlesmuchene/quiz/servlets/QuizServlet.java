package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.controllers.QuizController;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.console.ConsoleView;

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

    // TODO: 2019-02-01 Use session to store application state?
    private final QuizController controller = new QuizController(new ConsoleView(), new InMemoryData(),
            new ApplicationState());

    // TODO: Initialize controller in 'init' method?

    //TODO: 2019-02-01 Are we able to abstract the 3 required view steps to Application interface?
    // -> showNextQuestion
    // -> validateAnswer
    // -> getNextInput

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
