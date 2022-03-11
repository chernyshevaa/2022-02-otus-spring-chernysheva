package ru.otus.homework1.domain;

public class Answer {
    private final String text;
    private final Boolean isCorrect;

    public Answer(String text, Boolean isCorrect) {
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
