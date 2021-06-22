package ru.entity;

import java.util.List;

public class Question {
    Integer id;
    String name;
    QuestionType questionType;
    List<QuestionOption> questionOptions;


    public Question(int id, String name, QuestionType questionType, List<QuestionOption> questionOptions) {
        this.id = id;
        this.name = name;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
    }

    public Question(int id, String name, QuestionType questionType) {
        this.id = id;
        this.name = name;
        this.questionType = questionType;
    }
    public Question () {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOption> getAnswerForQuestionList() {
        return questionOptions;
    }

    public void setAnswerForQuestionList(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
