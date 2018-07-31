package com.stroganova.userstore.dao.jdbc;

import com.stroganova.userstore.dao.UserDao;
import com.stroganova.userstore.dao.jdbc.mapper.UserRowMapper;
import com.stroganova.userstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {

    private final static String GET_ALL_USERS_SQL = "SELECT id, name, salary, dateOfBirth FROM users";
    private final static String GET_USER_BY_ID_SQL = "SELECT id, name, salary, dateOfBirth FROM users WHERE id = ?";
    private final static String INSERT_USER_SQL = "INSERT INTO users (name, salary, dateOfBirth) VALUES (?, ?, ?);";
    private final static String UPDATE_USER_BY_ID_SQL = "UPDATE users SET name = ?, salary = ?, dateOfBirth = ? WHERE id = ?;";
    private final static String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id = ?";

    private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private ConnectionManager connectionManager;

    public List<User> getAll() {
        try (Connection connection = connectionManager.getConnection();
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
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_SQL)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while inserting user", e);
        }
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while deleting", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {

            statement.setString(1, user.getName());
            statement.setDouble(2, user.getSalary());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("error while updating", e);
        }
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
