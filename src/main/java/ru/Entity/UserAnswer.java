package ru.Entity;

import java.util.List;

public class UserAnswer {
    int id;
    String text;
    QuestionType questionType;
    QuestionOption questionOption;
    List<QuestionOption> questionOptions;

    public UserAnswer(int id, String text, QuestionType questionType) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
    }

    public UserAnswer(int id, String text, QuestionType questionType, QuestionOption questionOption) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
        this.questionOption = questionOption;
    }

    public UserAnswer(int id, String text, QuestionType questionType, List<QuestionOption> questionOptions) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
    }
}
