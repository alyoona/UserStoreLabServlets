package com.servletlabusers.dao.jdbc;

import com.servletlabusers.dao.UserDao;
import com.servletlabusers.dao.jdbc.mapper.UserRowMapper;
import com.servletlabusers.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {

    private final static String GET_ALL_USERS_SQL = "SELECT id, name, salary FROM users";
    private final static String GET_USER_BY_ID = "SELECT id, name, salary FROM users WHERE ID = ?";
    private final static String INSERT_USER = "INSERT INTO USERS (name, salary) VALUES (?, ?);";
    private final static String UPDATE_USER_BY_ID = "UPDATE USERS SET name = ?, salary = ? WHERE ID = ?;";
    private final static String DELETE_USER_BY_ID = "DELETE FROM USERS WHERE ID = ?";

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL)) {

            UserRowMapper userRowMapper = new UserRowMapper();
            while (resultSet.next()) {
                User user = userRowMapper.mapRow(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("error while getting all users", e);
        }
        return userList;
    }

    @Override
    public User getById(long id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                UserRowMapper userRowMapper = new UserRowMapper();
                while (resultSet.next()) {
                    user = userRowMapper.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("error while getting all users", e);
        }
        return user;
    }

    @Override
    public int add(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while inserting user", e);
        }
    }

    @Override
    public int delete(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {

            statement.setLong(1, user.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while deleting", e);
        }
    }

    @Override
    public int update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            statement.setLong(3, user.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while updating", e);
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("error while loading driver", e);
        }
        String dbURL = "jdbc:sqlite:db/users.db";
        try {
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            throw new RuntimeException("error while getting connection", e);
        }
    }

}
