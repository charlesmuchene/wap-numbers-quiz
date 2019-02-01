package com.charlesmuchene.quiz.console;

import com.charlesmuchene.quiz.views.View;

/**
 * Console {@link View} implementation
 */
public class ConsoleView implements View {

    @Override
    public void displayText(String text, int score) {
        String builder = System.lineSeparator() + "Your current score is: " + score +
                "." + System.lineSeparator() +
                "Guess the next number in the following sequence!" +
                System.lineSeparator() + text +
                System.lineSeparator() + "Your answer: ";
        System.out.print(builder);
    }

    @Override
    public void questionsOver(int score) {
        String text = System.lineSeparator() + "Quiz over." + System.lineSeparator() +
                "Your final score is: " + score;
        System.out.println(text);
    }

    @Override
    public void displayIncorrectAnswer(String text, int score) {
        String output = System.lineSeparator() + "Incorrect answer!" + System.lineSeparator();
        System.err.println(output);
    }
}
