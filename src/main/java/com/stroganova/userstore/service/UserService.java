package com.stroganova.userstore.service;

import com.stroganova.userstore.dao.UserDao;
import com.stroganova.userstore.entity.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void add(User user) {
        userDao.add(user);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User getById(long id) {
        return userDao.getById(id);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}




