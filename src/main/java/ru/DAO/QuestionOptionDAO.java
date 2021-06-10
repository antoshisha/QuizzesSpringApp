package ru.DAO;

import ru.Entity.QuestionOption;

public interface QuestionOptionDAO {
    void createQuestionOption(QuestionOption questionOption);
    void updateQuestionOption(QuestionOption questionOption);
    void deleteQuestion(int questionOptionId);
}
