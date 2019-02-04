package com.charlesmuchene.quiz.console;

import com.charlesmuchene.quiz.presentation.Presentation;

/**
 * Console {@link Presentation} implementation
 */
public class ConsolePresentation implements Presentation {

    @Override
    public void displayQuestionText(String text, int score) {
        String builder = System.lineSeparator() + "Your current score is: " +
                score + "." + System.lineSeparator() +
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

    @Override
    public void displayInputAsInvalid(String text, int score) {
        String output = System.lineSeparator() + "Invalid input!";
        System.err.println(output);
        output = System.lineSeparator() +
                "Try again. (Enter answer as a number)" +
                System.lineSeparator() + "Your answer: ";
        System.out.print(output);
    }
}
