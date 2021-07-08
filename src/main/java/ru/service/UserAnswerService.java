package ru.service;

import org.springframework.stereotype.Component;
import ru.dao.UserAnswerDAOImpl;
import ru.entity.UserAnswer;

import java.util.List;

@Component
public class UserAnswerService {
    private final UserAnswerDAOImpl userAnswerDAO;

    public UserAnswerService(UserAnswerDAOImpl userAnswerDAO) {
        this.userAnswerDAO = userAnswerDAO;
    }

    public void createUserAnswer(UserAnswer userAnswer) {
        userAnswerDAO.createUserAnswer(userAnswer);
    }

    public void batchCreateUserAnswers(List<UserAnswer> answerList) {
    userAnswerDAO.batchCreateUserAnswers(answerList);
    }
}
