package ru.DAO;

import org.springframework.stereotype.Component;
import ru.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class UserDAOImpl implements UserDAO {
    private final ConnectionDB connectionDB;

    public UserDAOImpl(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public void createUser(User user) {
        Connection conn = connectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO users (id, name_is_anonymous) VALUES (?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setBoolean(2, user.isAnonymous());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
            } catch (SQLException x) {
                x.printStackTrace();
            }
        } try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
