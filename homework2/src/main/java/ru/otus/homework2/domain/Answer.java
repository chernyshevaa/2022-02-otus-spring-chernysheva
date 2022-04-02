package ru.otus.homework2.domain;

public class Answer {
    private final String text;
    private final boolean isCorrect;

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }
}
