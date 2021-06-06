package ru.DAO;

import ru.Entity.Question;

public interface QuestionDAO {
    void addQuestion(Question question, int quizId);
    void updateQuestion(Question question);
    void deleteQuestion(int id);
}
