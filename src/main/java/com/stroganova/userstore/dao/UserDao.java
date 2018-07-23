package com.stroganova.userstore.dao;

import com.stroganova.userstore.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getById(long id);

    void add(User user);

    void deleteById(long id);

    void update(User user);

    void createUsers();
}
