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
        Connection connection = Util.Connection();
        try {
            connection.setAutoCommit(false);
            connection
                    .createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS `first`.`users` (\n" +
                            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                            "  `name` VARCHAR(45) NOT NULL,\n" +
                            "  `lastName` VARCHAR(45) NOT NULL,\n" +
                            "  `age` SMALLINT NOT NULL,\n" +
                            "  PRIMARY KEY (`id`));");
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в createUsersTable");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    System.out.println("Ошибка в connection.close();");
                    exception.printStackTrace();

                }
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.Connection();
        try {
            connection.setAutoCommit(false);
            connection
                    .createStatement()
                    .execute("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в dropUsersTable");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    System.out.println("Ошибка в connection.close();");
                    exception.printStackTrace();

                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.Connection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);

            User user = new User(name, lastName, age);

            preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + user.getName() + "  добавлен в базу данных");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в saveUser");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    System.out.println("Ошибка в connection.close();");
                    exception.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.Connection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в removeUserById");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    System.out.println("Ошибка в connection.close();");
                    exception.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        Connection connection = Util.Connection();
        try {
            connection.setAutoCommit(false);

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
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в getAllUsers");
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    System.out.println("Ошибка в connection.close();");
                    exception.printStackTrace();

                }
            }
        }
        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.Connection();
        try {
            connection.setAutoCommit(false);
            connection
                    .createStatement()
                    .execute("DELETE FROM users");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Ошибка в cleanUsersTable");
            ex.printStackTrace();
        }
    }
}