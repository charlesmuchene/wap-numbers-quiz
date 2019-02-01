package com.charlesmuchene.quiz.views;

/**
 * Console {@link View} implementation
 */
public class ConsoleView implements View {

    @Override
    public void displayText(String text, String score) {
        String builder = "Your current score is: " + score +
                "." + System.lineSeparator() +
                "Guess the next number in the sequence!" +
                System.lineSeparator() + text +
                "Your answer:";
        System.out.println(builder);
    }

    @Override
    public void questionsOver(String score) {
        String text = "Questions are over!" + System.lineSeparator() +
                "Your final score is: " + score + "!";
        System.out.println(text);
    }
}
