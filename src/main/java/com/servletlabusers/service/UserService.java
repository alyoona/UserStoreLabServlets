package com.servletlabusers.service;

import com.servletlabusers.dao.UserDao;
import com.servletlabusers.entity.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public int addUser(User user) {
        return userDao.add(user);
    }

    public int delete(User user) {
        return userDao.delete(user);
    }

    public int update(User user) {
        return userDao.update(user);
    }

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void setJdbcUserDao(UserDao jdbcUserDao) {
        this.userDao = jdbcUserDao;
    }
}




