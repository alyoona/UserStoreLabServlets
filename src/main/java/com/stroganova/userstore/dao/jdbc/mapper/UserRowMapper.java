package com.stroganova.userstore.dao.jdbc.mapper;

import com.stroganova.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.time.ZonedDateTime;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSalary(resultSet.getDouble("salary"));
        user.setDateOfBirth(getLocalDate(resultSet.getDate("dateOfBirth")));
        return user;
    }


    private LocalDate getLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime()); /*date.toLocalDate(); has comment: "/deprecation/" */
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        return localDate;
    }
}
