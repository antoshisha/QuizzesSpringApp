package ru.dao;

import ru.entity.QuestionOption;

import java.util.List;

public interface QuestionOptionDAO {
    void createQuestionOption(QuestionOption questionOption);
    void updateQuestionOption(QuestionOption questionOption);
    void deleteQuestionOption(int questionOptionId);
    void batchCreateQuestionOptions(List<QuestionOption> questionOptionList);
    void batchUpdateQuestionOptions(List<QuestionOption> questionOptionList);
    void batchDeleteQuestionOptions(List<QuestionOption> questionOptionList);
    List<QuestionOption> getOptionsForQuestion(int questionId);
}
