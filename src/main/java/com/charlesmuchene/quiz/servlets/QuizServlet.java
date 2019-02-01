package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.business.Quiz;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.servlets.servlet.ServletView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Quiz Servlet
 */
@WebServlet(name = "quiz", urlPatterns = "/quiz")
public class QuizServlet extends HttpServlet {

    private static final String SAME_REQUEST = "same_request";
    private Quiz quiz;

    @Override
    public void init() throws ServletException {
        super.init();
        QuestionDAO dao = new InMemoryData();
        ApplicationState state = new ApplicationState();
        quiz = new Quiz(null, dao, state);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute(SAME_REQUEST, true);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        quiz.setView(new ServletView(response.getWriter()));
        boolean start = request.getParameterMap().containsKey("start");
        Object sameRequest = request.getAttribute(SAME_REQUEST);
        if (!start) {
            String input = request.getParameter("answer");
            if (input != null) {
                validateAnswer(input, sameRequest);
                return;
            }
        }
        displayQuestion(sameRequest);
    }

    private void displayQuestion(Object sameRequest) {
        boolean nextQuestion = sameRequest == null ? quiz.displayNextQuestion() : quiz.displayCurrentQuestion();
        if (!nextQuestion) quiz.resetState();
    }

    /**
     * Validate user's answer
     *
     * @param input       Input
     * @param sameRequest Same request
     */
    private void validateAnswer(String input, Object sameRequest) {
        Optional<Integer> answer = quiz.sanitizeInput(input);
        if (answer.isPresent()) {
            boolean isCorrect = quiz.validateAnswer(answer.get());
            if (isCorrect) displayQuestion(sameRequest);
            else quiz.displayIncorrectAnswer();
        } else quiz.displayInvalidInput();
    }
}
