package com.charlesmuchene.quiz.console;

import com.charlesmuchene.quiz.controllers.QuizController;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.views.View;

import java.util.Scanner;

/**
 * Console application
 * <p>
 * Uses Standard Input, Output and Error Streams
 */
public class ConsoleApplication {

    private final QuizController controller;
    private final Scanner scanner;

    /**
     * Constructor
     *
     * @param controller {@link QuizController} instance
     */
    private ConsoleApplication(QuizController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display next question
     *
     * @return {@code true} if there's a question to show
     * {@code false} otherwise
     */
    private boolean showNextQuestion() {
        boolean hasNextQuestion = controller.displayNextQuestion();
        if (!hasNextQuestion) {
            scanner.close();
            controller.resetState();
        }
        return hasNextQuestion;
    }

    /**
     * Validate the given answer
     *
     * @param answer Answer to validate
     */
    private void validateAnswer(int answer) {
        controller.validateAnswer(answer);
    }

    /**
     * Acquire input from user and sanitize it.
     * <p>
     * NB: Never trust the user! :D
     *
     * @return Valid user input
     */
    private int getNextInput() {
        int answer;
        while (true) {
            String line = scanner.nextLine();
            try {
                answer = Integer.parseInt(line);
                break;
            } catch (NumberFormatException exception) {
                String text = System.lineSeparator() + "Invalid answer!";
                System.err.println(text);
                text = System.lineSeparator() +
                        "Try again. (Enter answer as a number)" +
                        System.lineSeparator() + "Your answer: ";
                System.out.print(text);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        QuestionDAO questionDAO = new InMemoryData();
        ApplicationState applicationState = new ApplicationState();
        QuizController controller = new QuizController(consoleView, questionDAO, applicationState);
        ConsoleApplication application = new ConsoleApplication(controller);

        while (true) {
            boolean nextQuestion = application.showNextQuestion();
            if (!nextQuestion) break;
            int answer = application.getNextInput();
            application.validateAnswer(answer);
        }
    }

}
