package ru.dao;

import org.springframework.stereotype.Component;
import ru.entity.QuestionOption;

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
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE answer_option SET question_id= ?, option= ? WHERE id= ?");
            preparedStatement.setInt(1, questionOption.getQuestionId());
            preparedStatement.setString(2, questionOption.getOption());
            preparedStatement.setInt(3, questionOption.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteQuestionOption(int questionOptionId) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM answer_option WHERE id= ?");
            preparedStatement.setInt(1, questionOptionId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
