package ru.dao;

import ru.entity.Question;

public interface QuestionDAO {
    void createQuestion(Question question, int quizId);
    void updateQuestion(Question question, int quizId);
    void deleteQuestion(int id);
    void deleteQuestionByQuizId(int quizId);
}
