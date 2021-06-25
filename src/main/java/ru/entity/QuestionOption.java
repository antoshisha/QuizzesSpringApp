package ru.entity;

public class QuestionOption {
    int id;
    int questionId;
    String option;

    public QuestionOption(int id, int questionId, String option) {
        this.id = id;
        this.questionId = questionId;
        this.option = option;
    }

    public QuestionOption() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
