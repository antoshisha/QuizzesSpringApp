package ru.dao;


import ru.entity.Quiz;

import java.util.List;

public interface QuizDAO {
    void createQuiz(Quiz quiz);
    void updateQuiz(Quiz quiz);
    void deleteQuiz(int id);
    List<Quiz> getAll();
    List<Quiz> getQuizForUserId( int userId);
    Quiz getQuiz(int quizId);
}
