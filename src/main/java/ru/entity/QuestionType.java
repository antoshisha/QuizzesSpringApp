package ru.entity;

public enum QuestionType {
    TEXT_ANSWER("1"),
    ONE_CHOICE("2"),
    MULTIPLE_CHOICE("3");

    private final String displayValue;

    QuestionType(String displayValue) {

        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
