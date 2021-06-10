package ru.DAO;

import org.springframework.stereotype.Component;
import ru.Entity.Question;
import ru.Entity.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Component
public class QuizDAOImpl implements QuizDAO {
    private final ConnectionDB connectionDB;

    public QuizDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void createQuiz(Quiz quiz) {
        Connection conn = connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO " + "quiz (id, name, start, finish, description) VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setString(2, quiz.getName());
            preparedStatement.setDate(3, quiz.getStartDate());
            preparedStatement.setDate(4, quiz.getFinishDate());
            preparedStatement.setString(5, quiz.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
    public void updateQuiz(Quiz quiz) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE quiz SET id= ?, name= ?, start= ?, finish= ?, description= ?) ")
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteQuiz(int id) {

    }

    @Override
    public List<Quiz> getAll() {
        return null;
    }

    @Override
    public Quiz getForId(int userId) {
        return null;
    }
}
