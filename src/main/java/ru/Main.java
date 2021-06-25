package ru;

import ru.entity.Question;

public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        System.out.println(question.getQuestionType().name());
    }
}
