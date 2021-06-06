package ru.Entity;

import java.util.List;

public class Question {
    int id;
    String name;
    QuestionType questionType;
    List<AnswerForQuestion> answerForQuestionList;


    public Question(int id, String name, QuestionType questionType, List<AnswerForQuestion> answerForQuestionList) {
        this.id = id;
        this.name = name;
        this.questionType = questionType;
        this.answerForQuestionList = answerForQuestionList;
    }

    public Question(int id, String name, QuestionType questionType) {
        this.id = id;
        this.name = name;
        this.questionType = questionType;
    }

    public int getId() {
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

    public List<AnswerForQuestion> getAnswerForQuestionList() {
        return answerForQuestionList;
    }

    public void setAnswerForQuestionList(List<AnswerForQuestion> answerForQuestionList) {
        this.answerForQuestionList = answerForQuestionList;
    }
}
