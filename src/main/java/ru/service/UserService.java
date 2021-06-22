package ru.service;

import ru.dao.UserDAOImpl;
import ru.entity.User;

public class UserService {
    private final UserDAOImpl userDAO;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }
}
