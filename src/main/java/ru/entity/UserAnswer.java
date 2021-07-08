package ru.entity;

import org.springframework.stereotype.Component;


public class UserAnswer {
    int userAnswerId;
    int userId;
    int questionId;
    String text;
    int questionOptionId;
    String questionOption;

    public String getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(String questionOption) {
        this.questionOption = questionOption;
    }



    public UserAnswer(int userAnswerId, int userId, int questionId, String text, int questionOptionId, String questionOption) {
        this.userAnswerId = userAnswerId;
        this.userId = userId;
        this.questionId = questionId;
        this.text = text;
        this.questionOptionId = questionOptionId;
        this.questionOption = questionOption;
    }

    public UserAnswer(int userAnswerId, int userId, int questionId, String text, int questionOptionId) {
        this.userAnswerId = userAnswerId;
        this.userId = userId;
        this.questionId = questionId;
        this.text = text;
        this.questionOptionId = questionOptionId;
    }

    public UserAnswer() {

    }

    public int getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(int userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuestionOptionId() {
        return questionOptionId;
    }

    public void setQuestionOptionId(int questionOptionId) {
        this.questionOptionId = questionOptionId;
    }
}
