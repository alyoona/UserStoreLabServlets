package com.servletlabusers.dao.jdbc;

import com.servletlabusers.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class JdbcUserDaoITest {

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
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.getById(91);
        System.out.println(user);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("Lera");
        user.setSalary(1777.0);

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> listBeforeAdding = jdbcUserDao.getAll();
        assertFalse(listBeforeAdding.contains(user));
        assertEquals(1, jdbcUserDao.add(user));
        List<User> listAfterAdding = jdbcUserDao.getAll();
        for(User user1 : listAfterAdding) {
            System.out.println(user1);
        }
        assertTrue(listAfterAdding.contains(user));
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
        User user = new User();
        user.setId(25);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        System.out.println(jdbcUserDao.delete(user));
    }

}