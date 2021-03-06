package ru.dao;

import org.springframework.stereotype.Component;
import ru.entity.Question;
import ru.entity.UserAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserAnswerDAOImpl implements UserAnswerDAO {
    private final ConnectionDB connectionDB;

    public UserAnswerDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void createUserAnswer(UserAnswer userAnswer) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_answer (id, user_id, question_id, text, answer_option_id) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, userAnswer.getUserAnswerId());
            preparedStatement.setInt(2, userAnswer.getUserId());
            preparedStatement.setInt(3, userAnswer.getQuestionId());
            preparedStatement.setString(4, userAnswer.getText());
            preparedStatement.setInt(5, userAnswer.getQuestionOptionId());
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

    public void batchCreateUserAnswers(List<UserAnswer> answerList) {
        Connection conn = connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO user_answer (id, user_id, question_id, text, answer_option_id) VALUES (?,?,?,?,?)");
            for (UserAnswer userAnswer : answerList) {
                preparedStatement.setInt(1, userAnswer.getUserAnswerId());
                preparedStatement.setInt(2, userAnswer.getUserId());
                preparedStatement.setInt(3, userAnswer.getQuestionId());
                preparedStatement.setString(4, userAnswer.getText());
                preparedStatement.setInt(5, userAnswer.getQuestionOptionId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
                System.out.println("----INSERT QUERY IS NOT APPLIED---");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.close();
            System.out.println("----INSERT QUERY IS SUCCESSFUL---");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
