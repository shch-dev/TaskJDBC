package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/first?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASS = "Ivan.22.8.";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection Connection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER); //подключаем драйвер jdbc mysql
            connection = DriverManager.getConnection(URL, LOGIN, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
