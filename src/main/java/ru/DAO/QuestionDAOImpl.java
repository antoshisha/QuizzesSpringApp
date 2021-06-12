package ru.DAO;

import org.springframework.stereotype.Component;
import ru.Entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class QuestionDAOImpl implements QuestionDAO {
    private final ConnectionDB connectionDB;

    public QuestionDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void createQuestion(Question question, int quizId) {
        Connection conn = connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO " + "question (id, name, quiz_id, type) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getName());
            preparedStatement.setInt(3, quizId);
            preparedStatement.setInt(4, question.getQuestionType().ordinal() +1 );
            preparedStatement.executeUpdate();

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

    @Override
    public void updateQuestion(Question question, int quizId) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE question SET name= ?, quiz_id= ?, type= ? WHERE id= ?");
            preparedStatement.setString(1, question.getName());
            preparedStatement.setInt(2, quizId);
            preparedStatement.setInt(3, question.getQuestionType().ordinal()+1);
            preparedStatement.setInt(4, question.getId());
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
    public void deleteQuestion(int id) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM question WHERE id = ?");
            preparedStatement.setInt(1, id);
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
