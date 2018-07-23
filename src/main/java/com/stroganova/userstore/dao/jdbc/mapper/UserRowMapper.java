package com.stroganova.userstore.dao.jdbc.mapper;

import com.stroganova.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSalary(resultSet.getDouble("salary"));
        return user;
    }
}
