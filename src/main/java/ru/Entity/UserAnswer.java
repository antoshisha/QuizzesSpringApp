package ru.Entity;

import java.util.List;

public class UserAnswer {
    int id;
    String text;
    QuestionType questionType;
    AnswerForQuestion answerForQuestion;
    List<AnswerForQuestion> answerForQuestionList;

    public UserAnswer(int id, String text, QuestionType questionType) {
        this.id = id;
        this.text = text;
        this.questionType = questionType;
    }

    public UserAnswer(int id, QuestionType questionType, AnswerForQuestion answerForQuestion) {
        this.id = id;
        this.questionType = questionType;
        this.answerForQuestion = answerForQuestion;
    }

    public UserAnswer(int id, QuestionType questionType, List<AnswerForQuestion> answerForQuestionList) {
        this.id = id;
        this.questionType = questionType;
        this.answerForQuestionList = answerForQuestionList;
    }
}
