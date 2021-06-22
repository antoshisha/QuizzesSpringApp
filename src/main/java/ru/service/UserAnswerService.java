package ru.service;

import ru.dao.UserAnswerDAOImpl;
import ru.entity.UserAnswer;

public class UserAnswerService {
    private final UserAnswerDAOImpl userAnswerDAO;

    public UserAnswerService(UserAnswerDAOImpl userAnswerDAO) {
        this.userAnswerDAO = userAnswerDAO;
    }

    public void createUserAnswer(UserAnswer userAnswer) {
        userAnswerDAO.createUserAnswer(userAnswer);
    }
}
