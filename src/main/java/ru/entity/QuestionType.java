package ru.entity;

public enum QuestionType {
    TEXT_ANSWER("TEXT_ANSWER"),
    ONE_CHOICE("ONE_CHOICE"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE");

    private final String displayValue;

    QuestionType(String displayValue) {

        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
