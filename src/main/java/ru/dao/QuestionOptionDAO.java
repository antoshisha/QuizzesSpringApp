package ru.dao;

import ru.entity.QuestionOption;

public interface QuestionOptionDAO {
    void createQuestionOption(QuestionOption questionOption);
    void updateQuestionOption(QuestionOption questionOption);
    void deleteQuestionOption(int questionOptionId);
}
