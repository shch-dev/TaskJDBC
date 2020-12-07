package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.Connection()) {
            connection
                    .createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS `first`.`users` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(45) NOT NULL,\n" +
                            "  `lastName` VARCHAR(45) NOT NULL,\n" +
                            "  `age` SMALLINT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`));");

        } catch (SQLException ex) {
            System.out.println("Ошибка в createUsersTable");
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.Connection()) {
            connection
                    .createStatement()
                    .execute("DROP TABLE IF EXISTS users");
        } catch (SQLException ex) {
            System.out.println("Ошибка в dropUsersTable");
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.Connection()) {
            User user = new User(name, lastName, age);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ошибка в saveUser");
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.Connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Ошибка в removeUserById");
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        try (Connection connection = Util.Connection()) {
            ResultSet resultSet = connection
                    .createStatement()
                    .executeQuery("SELECT id, name, lastName, age FROM users");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка в getAllUsers");
            ex.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.Connection()) {
            connection
                    .createStatement()
                    .execute("DELETE FROM users");
        } catch (SQLException ex) {
            System.out.println("Ошибка в cleanUsersTable");
            ex.printStackTrace();
        }
    }
}
