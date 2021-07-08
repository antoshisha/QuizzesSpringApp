package ru.dao;

import ru.entity.UserAnswer;

import java.util.List;

public interface UserAnswerDAO {
    void createUserAnswer(UserAnswer userAnswer);
    void batchCreateUserAnswers(List<UserAnswer> answerList);
}
