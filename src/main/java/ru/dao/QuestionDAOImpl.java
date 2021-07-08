package ru.dao;

import org.springframework.stereotype.Component;
import ru.entity.Question;
import ru.entity.QuestionType;
import ru.entity.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDAOImpl implements QuestionDAO {
    private final ConnectionDB connectionDB;
    private final QuestionOptionDAO questionOptionDAO;

    public QuestionDAOImpl(ConnectionDB connectionDB, QuestionOptionDAO questionOptionDAO) {
        this.connectionDB = connectionDB;
        this.questionOptionDAO = questionOptionDAO;
    }

    @Override
    public void createQuestion(Question question, int quizId) {
        Connection conn = connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO " + "question (name, quiz_id, type) VALUES (?,?,?)");
            preparedStatement.setString(1, question.getName());
            preparedStatement.setInt(2, quizId);
            preparedStatement.setInt(3, question.getQuestionType().ordinal() +1 );
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

    @Override
    public void deleteQuestionByQuizId(int quizId) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM question WHERE quiz_id = ?");
            preparedStatement.setInt(1, quizId);
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
    public void batchCreateQuestions(List<Question> questionList, int quizId) {
        Connection conn = connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO " + "question (id, name, quiz_id, type) VALUES (?,?,?,?)");
            for (Question question :questionList) {
                preparedStatement.setInt(1, question.getId());
                preparedStatement.setString(2, question.getName());
                preparedStatement.setInt(3, quizId);
                preparedStatement.setInt(4, question.getQuestionType().ordinal() + 1);
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

    @Override
    public void batchUpdateQuestions(List<Question> questionList, int quizId) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE question SET name= ?, quiz_id= ?  WHERE id= ?");
            for (Question question: questionList) {
                preparedStatement.setString(1, question.getName());
                preparedStatement.setInt(2, quizId);
                preparedStatement.setInt(3, question.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
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
    public List<Question> getQuestionsForQuizId(int quizId) {
        Connection connection = connectionDB.getConnection();
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM question WHERE quiz_id = ?");
            preparedStatement.setInt(1, quizId);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questions.add(new Question(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), QuestionType.values()[rs.getInt(4) - 1]));
            }
            preparedStatement.close();
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
        return questions;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        Connection connection = connectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM question");
            while (resultSet.next()) {
                questions.add(new Question(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3),  QuestionType.values()[resultSet.getInt(4) - 1]));
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
        return questions;
    }
    @Override
    public Question getQuestion(int questionId) {
        Connection connection = connectionDB.getConnection();
        Question question = new Question();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM quiz WHERE id = ?");
            preparedStatement.setInt(1, questionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                question.setId(rs.getInt(1));
                question.setName(rs.getString(2));
                question.setQuizId(rs.getInt(3));
                question.setQuestionType(QuestionType.values()[rs.getInt(4) - 1].name());

            }
            preparedStatement.close();
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
        return question;
    }
}
