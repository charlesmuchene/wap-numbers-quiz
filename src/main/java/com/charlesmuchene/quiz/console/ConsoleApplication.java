package com.charlesmuchene.quiz.console;

import com.charlesmuchene.quiz.business.Quiz;
import com.charlesmuchene.quiz.data.ApplicationState;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.views.View;

import java.util.Optional;
import java.util.Scanner;

/**
 * Console application
 * <p>
 * Uses Standard Input, Output and Error Streams
 */
public class ConsoleApplication {

    private final Quiz quiz;
    private final Scanner scanner;

    /**
     * Constructor
     *
     * @param quiz {@link Quiz} instance
     */
    private ConsoleApplication(Quiz quiz) {
        this.quiz = quiz;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display next question
     *
     * @return {@code true} if there's a question to show
     * {@code false} otherwise
     */
    private boolean showNextQuestion() {
        boolean hasNextQuestion = quiz.displayNextQuestion();
        if (!hasNextQuestion) {
            scanner.close();
            quiz.resetState();
        }
        return hasNextQuestion;
    }

    /**
     * Validate the given answer
     *
     * @param answer Answer to validate
     */
    private void validateAnswer(int answer) {
        quiz.validateAnswer(answer);
    }

    /**
     * Acquire input from user
     *
     * @return Valid user input
     */
    private int getNextInput() {
        int answer;
        while (true) {
            String line = scanner.nextLine();
            Optional<Integer> input = quiz.sanitizeInput(line);
            if (input.isPresent()) {
                answer = input.get();
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        QuestionDAO questionDAO = new InMemoryData();
        ApplicationState applicationState = new ApplicationState();
        Quiz controller = new Quiz(consoleView, questionDAO, applicationState);
        ConsoleApplication application = new ConsoleApplication(controller);

        while (true) {
            boolean nextQuestion = application.showNextQuestion();
            if (!nextQuestion) break;
            int answer = application.getNextInput();
            application.validateAnswer(answer);
        }
    }

}
