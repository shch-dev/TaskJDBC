package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBCI = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJDBCI.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBCI.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBCI.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJDBCI.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBCI.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBCI.cleanUsersTable();
    }
}
