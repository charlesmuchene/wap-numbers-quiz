package com.charlesmuchene.quiz.console;

import com.charlesmuchene.quiz.controllers.QuizController;
import com.charlesmuchene.quiz.data.InMemoryData;
import com.charlesmuchene.quiz.data.QuestionDAO;
import com.charlesmuchene.quiz.views.ConsoleView;
import com.charlesmuchene.quiz.views.View;

import java.util.Scanner;

/**
 * Console application
 */
public class ConsoleApplication {

    private final QuizController controller;
    private int score = 0;
    private int currentQuestion = 1;
    private Scanner scanner = new Scanner(System.in);

    private ConsoleApplication(QuizController controller) {
        this.controller = controller;
    }

    private boolean showNextQuestion() {
        boolean hasNextQuestion = controller.displayNextQuestion(currentQuestion, score);
        if (!hasNextQuestion) scanner.close();
        return hasNextQuestion;
    }

    private void validateAnswer(int answer) {
        boolean isCorrect = controller.isCorrectAnswer(currentQuestion++, answer);
        if (isCorrect) score++;
        else controller.displayIncorrectAnswer();
    }

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
        QuizController controller = new QuizController(consoleView, questionDAO);
        ConsoleApplication application = new ConsoleApplication(controller);

        while (true) {
            boolean nextQuestion = application.showNextQuestion();
            if (!nextQuestion) break;
            int answer = application.getNextInput();
            application.validateAnswer(answer);
        }
    }

}
