package com.stroganova.userstore.dao.jdbc;

import com.stroganova.userstore.config.ApplicationProperties;
import com.stroganova.userstore.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class JdbcUserDaoITest {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private ConnectionManager connectionManager = new ConnectionManager(applicationProperties.getProperties());
    private JdbcUserDao jdbcUserDao;

    @Before
    public void before() {
        jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.setConnectionManager(connectionManager);
        jdbcUserDao.add(new User("Ivanov", 2500.0, LocalDate.of(1990,01,8)));
    }

    @Test
    public void testGetAll() {
        List<User> actualUserList = jdbcUserDao.getAll();
        assertFalse(actualUserList.isEmpty());
        for (User user : actualUserList) {
            assertNotNull(user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getDateOfBirth());
        }
    }

    @Test
    public void testGetByID() {
        List<User> actualUserList = jdbcUserDao.getAll();
        User endUser = actualUserList.get(actualUserList.size() - 1);
        long id = endUser.getId();
        assertEquals(new User("Ivanov", 2500.0, LocalDate.of(1990,01,8)), jdbcUserDao.getById(id));

    }

    @Test
    public void testAdd() {
        jdbcUserDao.add(new User("Lera", 1777.0, LocalDate.of(1990,01,8)));
        List<User> listAfterAdding = jdbcUserDao.getAll();
        assertTrue(listAfterAdding.contains(new User("Lera", 1777.0, LocalDate.of(1990,01,8))));
    }


    @Test
    public void testUpdate() {
        List<User> actualUserList = jdbcUserDao.getAll();
        User user = actualUserList.get(0);
        long userId = user.getId();
        user.setSalary(4790.0);
        user.setName("Olga");
        jdbcUserDao.update(user);
        user = jdbcUserDao.getById(userId);
        assertEquals(new User("Olga", 4790.0, LocalDate.of(1990,01,8)), user);
    }

    @Test
    public void testDeleteByID() {
        List<User> actualUserList = jdbcUserDao.getAll();
        User user = actualUserList.get(0);
        long id = user.getId();
        jdbcUserDao.deleteById(id);
        assertEquals(null, jdbcUserDao.getById(id));
    }

}