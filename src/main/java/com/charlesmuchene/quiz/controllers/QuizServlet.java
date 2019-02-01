package com.charlesmuchene.quiz.controllers;

import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Quiz Servlet
 * <p>
 * Main quiz controller
 */
@WebServlet(name = "Quiz", urlPatterns = "/quiz")
public class QuizServlet extends HttpServlet {

    private final Quiz quiz = new Quiz(new InMemoryData());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
