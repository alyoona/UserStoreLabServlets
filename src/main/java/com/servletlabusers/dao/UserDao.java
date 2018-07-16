package com.servletlabusers.dao;

import com.servletlabusers.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getById(long id);

    int add(User user);

    int delete(User user);

    int update(User user);
}
