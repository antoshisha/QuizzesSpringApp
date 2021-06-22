package ru.service;

import ru.dao.QuestionOptionDAOImpl;
import ru.entity.QuestionOption;

public class QuestionOptionService {
    private final QuestionOptionDAOImpl questionOptionDAO;

    public QuestionOptionService(QuestionOptionDAOImpl questionOptionDAO) {
        this.questionOptionDAO = questionOptionDAO;
    }

    public void createQuestionOption(QuestionOption questionOption) {
        questionOptionDAO.createQuestionOption(questionOption);
    }

    public void updateQuestionOption(QuestionOption questionOption) {
        questionOptionDAO.updateQuestionOption(questionOption);
    }

    public void deleteQuestionOption(int questionOptionId) {
        questionOptionDAO.deleteQuestionOption(questionOptionId);
    }
}
