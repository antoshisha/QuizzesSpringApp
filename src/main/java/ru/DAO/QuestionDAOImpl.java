package ru.DAO;

import org.springframework.stereotype.Component;
import ru.Entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Component
public class QuestionDAOImpl implements QuestionDAO {
    private final ConnectionDB connectionDB;

    public QuestionDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void addQuestion(Question question, int quizId) {
        Connection conn = connectionDB.getConnection();
        try{
            if (question.getAnswerForQuestionList() == null) {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO " + "question (id, name, quiz_id, type) VALUES (?,?,?,?)");
                preparedStatement.setInt(1, question.getId());
                preparedStatement.setString(2, question.getName());
                preparedStatement.setInt(3, quizId);
                preparedStatement.setString(4, question.getQuestionType().toString());
                preparedStatement.executeUpdate();
            } else {
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO " + "question (id, name, quiz_id, type) VALUES (?,?,?,?)");
                preparedStatement.setInt(1, question.getId());
                preparedStatement.setString(2, question.getName());
                preparedStatement.setInt(3, quizId);
                preparedStatement.setString(4, question.getQuestionType().toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
                System.out.println("----INSERT QUERY IS NOT APPLIED---");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.close();
            System.out.println("----INSERT QUERY IS SUCCESSFUL---");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestion(Question question) {

    }

    @Override
    public void deleteQuestion(int id) {

    }
}
