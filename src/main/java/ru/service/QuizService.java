package ru.service;

import org.springframework.stereotype.Component;
import ru.dao.QuestionDAOImpl;
import ru.dao.QuestionOptionDAOImpl;
import ru.dao.QuizDAOImpl;
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
//        List<Question> questionList = quiz.getQuestionList();
//        questionDAO.batchCreateQuestions(questionList, quiz.getId());
//        for (Question question : questionList) {
//            List<QuestionOption> questionOptionList =question.getAnswerForQuestionList();
//            questionOptionDAO.batchCreateQuestionOptions(questionOptionList);
//        }
    }

    public void updateQuiz(Quiz quiz) {
        quizDAO.updateQuiz(quiz);
        List<Question> questionList = quiz.getQuestionList();
        List<Question> createQuestions = new ArrayList<>();
        List<Question> updateQuestions = new ArrayList<>();
        for (Question question: questionList) {
            if (question.getId() == null) {
                createQuestions.add(question);
            } else {
                updateQuestions.add(question);
            }
            List<QuestionOption> questionOptionList = question.getAnswerForQuestionList();
            List<QuestionOption> createQuestionOptions = new ArrayList<>();
            List<QuestionOption> updateQuestionOptions = new ArrayList<>();
            for (QuestionOption x : questionOptionList) {
                if (x.getId() == null) {
                    createQuestionOptions.add(x);
                } else {
                    updateQuestionOptions.add(x);
                }
            }
            if (!updateQuestionOptions.isEmpty())
                questionOptionDAO.batchUpdateQuestionOptions(updateQuestionOptions);

            if (!createQuestionOptions.isEmpty())
                questionOptionDAO.batchCreateQuestionOptions(createQuestionOptions);
        }
        if (!createQuestions.isEmpty())
            questionDAO.batchCreateQuestions(createQuestions, quiz.getId());

        if (!updateQuestions.isEmpty())
            questionDAO.batchUpdateQuestions(updateQuestions, quiz.getId());

    }
    public void deleteQuiz(int quizId) {
        quizDAO.deleteQuiz(quizId);
        questionDAO.deleteQuestionByQuizId(quizId);

    }
    public List<Quiz> getQuizForUserId(int userId) {
        List<Quiz> quizList = quizDAO.getQuizForUserId(userId);
        return quizList;
    }
    public List<Quiz> getAll() {
        return quizDAO.getAll();
    }


}
