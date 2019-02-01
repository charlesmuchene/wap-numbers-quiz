package com.charlesmuchene.quiz.servlets;

import com.charlesmuchene.quiz.views.View;

import java.io.PrintWriter;

/**
 * Servlet view implementation
 */
public class ServletView implements View {

    private final PrintWriter out;

    ServletView(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void displayText(String text, int score) {
        showQuestion(text, score, false);
    }

    @Override
    public void questionsOver(int score) {
        out.print("<html> ");
        out.print("<head >");
        out.print("<title>NumberQuiz</title> ");
        out.print("</head> ");
        out.print("<body> ");
        out.print("<p style='color:red'>The number quiz is over!</p>");
        out.print("<p>Your current score is: ");
        out.print(score);
        out.print("</body>");
        out.print("</html> ");
    }

    @Override
    public void displayIncorrectAnswer(String text, int score) {
        showQuestion(text, score, true);
    }

    /**
     * Show question
     *
     * @param text                Question text to display
     * @param score               Running score
     * @param showIncorrectAnswer {@code true} to show incorrect answer
     *                            {@code false} otherwise
     */
    private void showQuestion(String text, int score, boolean showIncorrectAnswer) {
        out.print("<html>");
        out.print("<head>");
        out.print("	<title>NumberQuiz</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("	<form method='post'>");
        out.print("		<h3>Have fun with NumberQuiz!</h3>");
        out.print("<p>Your current score is: ");
        out.print(score + "</br></br>");
        out.print("<p>Guess the next number in the sequence! ");
        out.print(text + "</p>");

        if (showIncorrectAnswer)
            out.print("<p style='color:red'>Incorrect answer! Please try again</p> ");

        out.print("<p>Your answer:<input type='text' name='txtAnswer' value='' /></p> ");
        out.print("<p><input type='submit' name='btnNext' value='Next' /></p> ");
        out.print("</form>");
        out.print("</body></html>");
    }
}
