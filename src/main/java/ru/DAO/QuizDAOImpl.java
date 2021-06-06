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
    private final QuestionDAOImpl questionDAO;

    public QuizDAOImpl(ConnectionDB connectionDB, QuestionDAOImpl questionDAO) {
        this.connectionDB = connectionDB;
        this.questionDAO = questionDAO;
    }

    @Override
    public void addQuiz(Quiz quiz) {
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
        List<Question> questionList = quiz.getQuestionList();
        for (Question x : questionList) {
            questionDAO.addQuestion(x, quiz.getId());
        }

    }

    @Override
    public void updateQuiz(Quiz quiz) {

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
