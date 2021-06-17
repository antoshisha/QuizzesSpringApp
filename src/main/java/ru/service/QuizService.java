package ru.service;

import org.springframework.stereotype.Component;
import ru.dao.QuestionDAOImpl;
import ru.dao.QuestionOptionDAOImpl;
import ru.dao.QuizDAOImpl;
import ru.entity.Question;
import ru.entity.QuestionOption;
import ru.entity.Quiz;

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
        List<Question> questionList = quiz.getQuestionList();
        for (Question x : questionList) {
            questionDAO.createQuestion(x, quiz.getId());
            List<QuestionOption> questionOptionList = x.getAnswerForQuestionList();
            for (QuestionOption z : questionOptionList) {
                questionOptionDAO.createQuestionOption(z);
            }
        }
    }

    public void updateQuiz(Quiz quiz) {
        quizDAO.updateQuiz(quiz);
        List<Question> questionList = quiz.getQuestionList();
        for (Question x : questionList) {
            if (x.getId() != null)
            questionDAO.updateQuestion(x, quiz.getId());
            List<QuestionOption> questionOptionList = x.getAnswerForQuestionList();
            for (QuestionOption z : questionOptionList) {
                questionOptionDAO.updateQuestionOption(z);
            }
        }
    }
    public void deleteQuiz(int quizId) {
        quizDAO.deleteQuiz(quizId);
        questionDAO.deleteQuestionByQuizId(quizId);

    }

    public List<Quiz> getAll() {
        return quizDAO.getAll();
    }


}
