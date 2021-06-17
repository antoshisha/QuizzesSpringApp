package ru.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.entity.UserAnswer;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAnswerMapper implements RowMapper<UserAnswer> {

    @Override
    public UserAnswer mapRow(ResultSet resultSet, int i) throws SQLException {
        UserAnswer userAnswer = new UserAnswer(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getString(4),
                resultSet.getInt(5));
        return userAnswer;
    }
}
