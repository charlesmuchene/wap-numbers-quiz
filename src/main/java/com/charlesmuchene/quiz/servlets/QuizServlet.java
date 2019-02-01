package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.business.Quiz;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.servlets.jsp.JSPView;
import com.charlesmuchene.quiz.servlets.servlet.ServletView;
import com.charlesmuchene.quiz.views.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        setUpView(request, response);
        serveRequest(request);
    }

    /**
     * Serve the given request
     *
     * @param request {@link HttpServletRequest} instance
     */
    private void serveRequest(HttpServletRequest request) {
        Object sameRequest = request.getAttribute(SAME_REQUEST);
        boolean start = request.getParameterMap().containsKey("start");

        if (!start) {
            String input = request.getParameter("answer");
            if (input != null) {
                validateAnswer(input, sameRequest);
                return;
            }
        }
        displayQuestion(sameRequest);
    }

    /**
     * Display question
     *
     * @param sameRequest Same request
     */
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

    /**
     * Set up view to use
     *
     * @param request  {@link HttpServletRequest} instance
     * @param response {@link HttpServletResponse} instance
     * @throws IOException can be thrown
     */
    private void setUpView(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backend = retrieveBackendChoice(request);
        boolean useJsp = backend.equalsIgnoreCase("jsp");

        View view = useJsp ? new JSPView(request, response) : new ServletView(response.getWriter());
        quiz.setView(view);
    }

    /**
     * Retrieve backend choice
     *
     * @param request {@link HttpServletRequest} instance
     * @return Backend choice
     */
    private String retrieveBackendChoice(HttpServletRequest request) {

        String backend = request.getParameter("backend");
        HttpSession session = request.getSession();

        if (backend == null) {
            backend = (String) session.getAttribute("backend");
            if (backend == null) backend = "jsp";
        }

        session.setAttribute("backend", backend);

        return backend;
    }
}
