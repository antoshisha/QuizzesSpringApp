package ru.dao;

import org.springframework.stereotype.Component;
import ru.entity.Question;
import ru.entity.QuestionOption;
import ru.entity.QuestionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void batchCreateQuestionOptions(List<QuestionOption> questionOptionList) {
        Connection conn = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO answer_option (question_id, option) VALUES (?,?)");
            for (QuestionOption questionOption : questionOptionList) {
                preparedStatement.setInt(1, getQuestionLastSequence());
                preparedStatement.setString(2, questionOption.getOption());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int getQuestionLastSequence() {
        Connection connection = connectionDB.getConnection();
        int last_value = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM question_id_seq");
            while (resultSet.next()) {
                last_value = resultSet.getInt(1);
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
        return last_value;
    }

    @Override
    public void batchUpdateQuestionOptions(List<QuestionOption> questionOptionList) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE answer_option SET question_id= ?, option= ? WHERE id= ?");
            for (QuestionOption questionOption : questionOptionList) {
                preparedStatement.setInt(1, questionOption.getQuestionId());
                preparedStatement.setString(2, questionOption.getOption());
                preparedStatement.setInt(3, questionOption.getId());
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
    public void batchDeleteQuestionOptions(List<QuestionOption> questionOptionList) {
        Connection connection = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM answer_option WHERE id= ?");
            for (QuestionOption questionOption : questionOptionList) {
                preparedStatement.setInt(1, questionOption.getId());
                preparedStatement.addBatch();
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
    }

    @Override
    public List<QuestionOption> getOptionsForQuestion(int questionId) {
        Connection connection = connectionDB.getConnection();
        List<QuestionOption> questionOptions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM answer_option WHERE question_id = ?");
            preparedStatement.setInt(1, questionId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questionOptions.add(new QuestionOption(rs.getInt(1),
                        rs.getInt(2), rs.getString(3)));
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
        return questionOptions;


    }



}
