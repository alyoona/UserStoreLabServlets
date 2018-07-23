package com.stroganova.userstore.dao.jdbc;

import com.stroganova.userstore.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class JdbcUserDaoITest {
    JdbcUserDao jdbcUserDao;

    @Before
    public void before() {
        jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.add(new User("Ivanov", 2500.0));
    }

    @Test
    public void testGetAll() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> actualUserList = jdbcUserDao.getAll();
        assertFalse(actualUserList.isEmpty());
        for (User user : actualUserList) {
            assertNotNull(user.getId());
            assertNotNull(user.getName());
            assertNotNull(user.getSalary());
        }
    }

    @Test
    public void testGetByID() {
        List<User> actualUserList = jdbcUserDao.getAll();
        User endUser = actualUserList.get(actualUserList.size() - 1);
        System.out.println(endUser);
        long id = endUser.getId();
        assertEquals(new User("Ivanov", 2500.0), jdbcUserDao.getById(id));

    }

    @Test
    public void testAdd() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.add(new User("Lera", 1777.0));
        List<User> listAfterAdding = jdbcUserDao.getAll();
        assertTrue(listAfterAdding.contains(new User("Lera", 1777.0)));
    }


    @Test
    public void testUpdate() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.getById(86);
        System.out.println(user);
        user.setSalary(4790.0);
        System.out.println(user);
        jdbcUserDao.update(user);
        User user2 = jdbcUserDao.getById(86);
        System.out.println(user2);

    }

    @Test
    public void testDeleteByID() {
        List<User> actualUserList = jdbcUserDao.getAll();
        User user = actualUserList.get(0);
        System.out.println(user);
        long id = user.getId();
        jdbcUserDao.deleteById(id);
        assertEquals(null, jdbcUserDao.getById(id));
    }

}