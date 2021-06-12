package ru.DAO;

import ru.Entity.Question;

public interface QuestionDAO {
    void createQuestion(Question question, int quizId);
    void updateQuestion(Question question, int quizId);
    void deleteQuestion(int id);
}
