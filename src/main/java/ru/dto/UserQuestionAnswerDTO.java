package ru.dto;

import org.springframework.stereotype.Component;
import ru.entity.Question;
import ru.entity.UserAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserQuestionAnswerDTO {
    List<Question> questionList;
    List<UserAnswer> userAnswerList;
    List<String> list;
    List<String> textList;
    List<Integer> idListForTextAnswer;


    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public List<Integer> getIdListForTextAnswer() {
        return idListForTextAnswer;
    }

    public void setIdListForTextAnswer(List<Integer> idListForTextAnswer) {
        this.idListForTextAnswer = idListForTextAnswer;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public UserQuestionAnswerDTO () {
        questionList = new ArrayList<>();
        userAnswerList = new ArrayList<>();
        list = new ArrayList<>();
        textList = new ArrayList<>();
        idListForTextAnswer = new ArrayList<>();

    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public void addUserAnswer(UserAnswer userAnswer) {
        userAnswerList.add(userAnswer);
    }

    public void addString(String string) {
        list.add(string);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<UserAnswer> getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList) {
        this.userAnswerList = userAnswerList;
    }
}
