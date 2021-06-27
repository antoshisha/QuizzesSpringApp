package ru;

import ru.dao.QuestionDAOImpl;
import ru.entity.Question;
import ru.entity.QuestionType;

public class Main {
    public static void main(String[] args) {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        System.out.println(question.getQuestionType().name());
        QuestionType value = QuestionType.values()[2];
                System.out.println(value);
    }
}
