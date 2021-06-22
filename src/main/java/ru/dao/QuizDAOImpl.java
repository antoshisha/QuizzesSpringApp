package ru.dao;

import org.springframework.stereotype.Component;
import ru.entity.Quiz;

import java.sql.*;
import java.util.ArrayList;
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
                    "INSERT INTO " + "quiz (name, start, finish, description) VALUES (?,?,?,?)");
            //preparedStatement.setInt(1, quiz.getId());
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setDate(2, quiz.getStartDate());
            preparedStatement.setDate(3, quiz.getFinishDate());
            preparedStatement.setString(4, quiz.getDescription());
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE quiz SET name= ?, start= ?, finish= ?, description= ? WHERE id= ?");
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setDate(2, quiz.getStartDate());
            preparedStatement.setDate(3, quiz.getFinishDate());
            preparedStatement.setString(4, quiz.getDescription());
            preparedStatement.setInt(5, quiz.getId());
            preparedStatement.executeUpdate();
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
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM quiz WHERE id= ?");
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

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quizzes = new ArrayList<>();
        Connection connection = connectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz");
            while (resultSet.next()) {
                quizzes.add(new Quiz(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5)));
            }
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
        return quizzes;
    }

    @Override
    public List<Quiz> getQuizForUserId(int userId) {
        Connection connection = connectionDB.getConnection();
        List<Quiz> userPastQuizzes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user_past_quizzes WHERE user_id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Integer> quizzesId = new ArrayList<>();
            while (resultSet.next()) quizzesId.add(resultSet.getInt(1));
            preparedStatement.close();
            for (Integer x : quizzesId) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(
                        "SELECT * FROM quiz WHERE id = ?");
                preparedStatement.setInt(1, x);
                ResultSet rs = preparedStatement2.getResultSet();
                userPastQuizzes.add(new Quiz(rs.getInt(1), rs.getString(2),
                        rs.getDate(3), rs.getDate(4), rs.getString(5)));
            }
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
        return userPastQuizzes;
    }
}
