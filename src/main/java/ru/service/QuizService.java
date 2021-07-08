package ru.service;

import org.springframework.stereotype.Component;
import ru.dao.QuestionDAOImpl;
import ru.dao.QuestionOptionDAOImpl;
import ru.dao.QuizDAOImpl;
import ru.dto.UserQuestionAnswerDTO;
import ru.entity.Question;
import ru.entity.QuestionOption;
import ru.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizService {
    private final QuizDAOImpl quizDAO;
    private final QuestionDAOImpl questionDAO;
    private final QuestionOptionDAOImpl questionOptionDAO;

    public QuizService(QuizDAOImpl quizDAO, QuestionDAOImpl questionDAO, QuestionOptionDAOImpl questionOptionDAO) {
        this.quizDAO = quizDAO;
        this.questionDAO = questionDAO;
        this.questionOptionDAO = questionOptionDAO;
    }

    public void createQuiz(Quiz quiz) {
        quizDAO.createQuiz(quiz);
    }

    public void updateQuiz(Quiz quiz) {
        quizDAO.updateQuiz(quiz);
        List<Question> questionList = quiz.getQuestionList();
        questionDAO.batchUpdateQuestions(questionList, quiz.getId());
        List<QuestionOption> updateQuestionOptions = new ArrayList<>();
        for (Question q : questionList) {
            List<QuestionOption> options = q.getQuestionOptions();
            updateQuestionOptions.addAll(options);
        }
        questionOptionDAO.batchUpdateQuestionOptions(updateQuestionOptions);

    }
    public void deleteQuiz(int quizId) {
        quizDAO.deleteQuiz(quizId);
        questionDAO.deleteQuestionByQuizId(quizId);

    }
    public List<Quiz> getQuizForUserId(int userId) {
        return quizDAO.getQuizForUserId(userId);
    }
    public List<Quiz> getAll() {
        return quizDAO.getAll();
    }

    public Quiz takeQuiz(int quizId) {
        List<Question> questions = questionDAO.getQuestionsForQuizId(quizId);
        for (Question x : questions) {
           x.setQuestionOptions(questionOptionDAO.getOptionsForQuestion(x.getId()));
        }
        Quiz quiz = quizDAO.getQuiz(quizId);
        quiz.setQuestionList(questions);
        return quiz;
    }

}
