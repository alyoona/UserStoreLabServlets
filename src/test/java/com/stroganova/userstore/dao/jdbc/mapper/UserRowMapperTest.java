package com.stroganova.userstore.dao.jdbc.mapper;

import com.stroganova.userstore.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        UserRowMapper userRowMapper = new UserRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("name")).thenReturn("Vanya");
        when(mockResultSet.getDouble("salary")).thenReturn(1200.0);

        User actual = userRowMapper.mapRow(mockResultSet);
        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("Vanya", actual.getName());
        assertEquals(1200.0, actual.getSalary(), 0.001);
    }
}