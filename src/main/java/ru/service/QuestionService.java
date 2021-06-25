package ru.service;

import org.springframework.stereotype.Component;
import ru.dao.QuestionDAOImpl;
import ru.dao.QuestionOptionDAOImpl;
import ru.entity.Question;
import ru.entity.QuestionOption;

import java.util.ArrayList;
import java.util.List;
@Component
public class QuestionService {
    private final QuestionDAOImpl questionDAO;
    private final QuestionOptionDAOImpl questionOptionDAO;

    public QuestionService(QuestionDAOImpl questionDAO, QuestionOptionDAOImpl questionOptionDAO) {
        this.questionDAO = questionDAO;
        this.questionOptionDAO = questionOptionDAO;
    }

    public void createQuestion(Question question, int quizId) {
        questionDAO.createQuestion(question, quizId);
        List<QuestionOption> questionOptions = question.getQuestionOptions();
        questionOptionDAO.batchCreateQuestionOptions(questionOptions);
    }

    public void updateQuestion(Question question, int quizId) {
        questionDAO.updateQuestion(question, quizId);

        List<QuestionOption> questionOptionList = question.getQuestionOptions();
        List<QuestionOption> createQuestionOptions = new ArrayList<>();
        List<QuestionOption> updateQuestionOptions = new ArrayList<>();

        for (QuestionOption x : questionOptionList) {
            if (x.getId() == null){
                createQuestionOptions.add(x);
            } else {
                updateQuestionOptions.add(x);
            }
        }

        if ( !updateQuestionOptions.isEmpty())
            questionOptionDAO.batchUpdateQuestionOptions(updateQuestionOptions);

        if ( !createQuestionOptions.isEmpty())
            questionOptionDAO.batchCreateQuestionOptions(createQuestionOptions);
    }

    public void deleteQuestion(int id) {
        questionDAO.deleteQuestion(id);
    }

    public void deleteQuestionByQuizId(int quizId) {
        questionDAO.deleteQuestionByQuizId(quizId);
    }
}
