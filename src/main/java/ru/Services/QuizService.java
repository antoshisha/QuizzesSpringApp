package ru.Services;

import org.springframework.stereotype.Component;
import ru.DAO.QuestionDAOImpl;
import ru.DAO.QuestionOptionDAOImpl;
import ru.DAO.QuizDAOImpl;
import ru.Entity.Question;
import ru.Entity.QuestionOption;
import ru.Entity.Quiz;

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
            questionDAO.updateQuestion(x, quiz.getId());
            List<QuestionOption> questionOptionList = x.getAnswerForQuestionList();
            for (QuestionOption z : questionOptionList) {
                questionOptionDAO.updateQuestionOption(z);
            }
        }
    }
    public void deleteQuiz(int quizUd) {
        quizDAO.deleteQuiz(quizUd);
        questionDAO.deleteQuestion();
    }


}
