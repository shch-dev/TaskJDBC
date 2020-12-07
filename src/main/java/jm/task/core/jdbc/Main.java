package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Ivan", "Ivanov", (byte) 5);
        userService.saveUser("Bob", "Marlow", (byte) 5);
        userService.saveUser("Max", "Maslow", (byte) 5);
        userService.saveUser("Vitalik", "Bobrov", (byte) 5);

        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
