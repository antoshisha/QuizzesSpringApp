package ru.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    Integer id;
    String name;
    int quizId;
    QuestionType questionType;
    List<QuestionOption> questionOptions =new ArrayList<>(4);


    public Question(int id, String name, QuestionType questionType, List<QuestionOption> questionOptions) {
        this.id = id;
        this.name = name;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
    }

    public Question(int id, String name, int quizId, QuestionType questionType) {
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

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = QuestionType.valueOf(questionType);
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public void addQuestionOption(QuestionOption questionOption) {
        this.questionOptions.add(questionOption);
    }



}
