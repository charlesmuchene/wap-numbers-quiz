package com.charlesmuchene.quiz.servlets.servlet;

import com.charlesmuchene.quiz.presentation.Presentation;

import java.io.PrintWriter;

/**
 * Servlet {@link Presentation} implementation
 */
public class ServletPresentation implements Presentation {

    private final PrintWriter out;

    public ServletPresentation(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void displayQuestionText(String text, int score, String hint) {
        showQuestion(text, score, false, false);
    }

    @Override
    public void questionsOver(int score) {
        out.print("<html> ");
        out.print("<head >");
        out.print("<title>NumberQuiz</title> ");
        out.print("</head> ");
        out.print("<body> ");
        out.print("<p style='color:green;'>Quiz is over!</p>");
        out.print("<p>Your current score is: ");
        out.print("<span style='font-weight: bold'>");
        out.print(score);
        out.print("</span>");
        out.print("</body>");
        out.print("</html> ");
    }

    @Override
    public void displayIncorrectAnswer(String text, int score, String hint) {
        showQuestion(text, score, true, false);
    }

    @Override
    public void displayInputAsInvalid(String text, int score, String hint) {
        showQuestion(text, score, false, true);
    }

    /**
     * Show question
     *
     * @param text                Question text to display
     * @param score               Running score
     * @param showIncorrectAnswer {@code true} to show incorrect answer {@code false} otherwise
     * @param showInvalidInput    {@code true} to show invalid input {@code false} otherwise
     */
    private void showQuestion(String text, int score, boolean showIncorrectAnswer, boolean showInvalidInput) {
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
            out.print("<p style='color:red'>Incorrect answer!</p> ");

        if (showInvalidInput)
            out.print("<p style='color:red'>Invalid input! Please try again</p> ");

        out.print("<p>Your answer:<input type='text' name='answer' value='' /></p> ");
        out.print("<p><input type='submit' name='next' value='Next' /></p> ");
        out.print("</form>");
        out.print("</body></html>");
    }
}
