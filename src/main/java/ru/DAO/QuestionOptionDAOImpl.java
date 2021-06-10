package ru.DAO;

import org.springframework.stereotype.Component;
import ru.Entity.QuestionOption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class QuestionOptionDAOImpl implements QuestionOptionDAO{
    private final ConnectionDB connectionDB;

    public QuestionOptionDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void createQuestionOption(QuestionOption questionOption) {
        Connection conn = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO answer_option (id, question_id, option) VALUES (?,?,?)");
            preparedStatement.setInt(1, questionOption.getId());
            preparedStatement.setInt(2, questionOption.getQuestionId());
            preparedStatement.setString(3, questionOption.getOption());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateQuestionOption(QuestionOption questionOption) {

    }

    @Override
    public void deleteQuestion(int questionOptionId) {

    }
}
