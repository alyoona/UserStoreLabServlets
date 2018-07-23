package com.stroganova.userstore.dao.jdbc;

import com.stroganova.userstore.dao.UserDao;
import com.stroganova.userstore.dao.jdbc.mapper.UserRowMapper;
import com.stroganova.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {

    private final static String CREATE_TABLE_USERS = "CREATE TABLE users (\n" +
            "  id SERIAL PRIMARY KEY,\n" +
            "  name VARCHAR(100),\n" +
            "  salary DECIMAL\n" +
            ");";


    private final static String GET_ALL_USERS_SQL = "SELECT id, name, salary FROM users";
    private final static String GET_USER_BY_ID = "SELECT id, name, salary FROM users WHERE id = ?";
    private final static String INSERT_USER = "INSERT INTO users (name, salary) VALUES (?, ?);";
    private final static String UPDATE_USER_BY_ID = "UPDATE users SET name = ?, salary = ? WHERE id = ?;";
    private final static String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    public List<User> getAll() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL)) {
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = USER_ROW_MAPPER.mapRow(resultSet);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("error while getting all users", e);
        }
    }

    @Override
    public User getById(long id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = USER_ROW_MAPPER.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("error while getting all users", e);
        }
        return user;
    }

    @Override
    public void add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while inserting user", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while deleting", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while updating", e);
        }
    }

    public void createUsers() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();)
        {
            statement.executeUpdate(CREATE_TABLE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException("error while creating table users, please uncomment line in the Starter.java //userDao.createUsers();", e);
        }

    }

    private Connection getConnection() {
        try {
            /*Class.forName("org.sqlite.JDBC");*/
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("error while loading driver", e);
        }
       /*String dbURL = "jdbc:sqlite:db/users.db";*/
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String password = "admin";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("error while getting connection", e);
        }
    }

}
