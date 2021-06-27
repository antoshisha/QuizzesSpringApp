package ru.dao;

import ru.entity.Question;

import java.util.List;

public interface QuestionDAO {
    void createQuestion(Question question, int quizId);
    void updateQuestion(Question question, int quizId);
    void deleteQuestion(int id);
    void deleteQuestionByQuizId(int quizId);
    void batchCreateQuestions(List<Question> questionList, int quizId);
    void batchUpdateQuestions(List<Question> questionList, int quizId);
    List<Question> getQuestionsForQuizId(int quizId);
    List<Question> getAllQuestions();
    Question getQuestion(int questionId);
}
