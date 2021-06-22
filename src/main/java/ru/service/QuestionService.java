package ru.service;

import ru.dao.QuestionDAOImpl;
import ru.dao.QuestionOptionDAOImpl;
import ru.entity.Question;
import ru.entity.QuestionOption;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    private final QuestionDAOImpl questionDAO;
    private final QuestionOptionDAOImpl questionOptionDAO;

    public QuestionService(QuestionDAOImpl questionDAO, QuestionOptionDAOImpl questionOptionDAO) {
        this.questionDAO = questionDAO;
        this.questionOptionDAO = questionOptionDAO;
    }

    public void createQuestion(Question question, int quizId) {
        questionDAO.createQuestion(question, quizId);
        List<QuestionOption> questionOptions = question.getAnswerForQuestionList();
        questionOptionDAO.batchCreateQuestionOptions(questionOptions);
    }

    public void updateQuestion(Question question, int quizId) {
        questionDAO.updateQuestion(question, quizId);

        List<QuestionOption> questionOptionList = question.getAnswerForQuestionList();
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
}
