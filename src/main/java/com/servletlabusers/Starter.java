package com.servletlabusers;

import com.servletlabusers.dao.UserDao;
import com.servletlabusers.dao.jdbc.JdbcUserDao;
import com.servletlabusers.service.UserService;
import com.servletlabusers.web.servlet.AddUserServlet;
import com.servletlabusers.web.servlet.DeleteUserServlet;
import com.servletlabusers.web.servlet.UpdateUserServlet;
import com.servletlabusers.web.servlet.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {

    public static void main(String[] args) throws Exception {

        UserDao userDao = new JdbcUserDao();

        UserService userService = new UserService();
        userService.setJdbcUserDao(userDao);

        UsersServlet usersServlet = new UsersServlet();
        usersServlet.setUserService(userService);


        AddUserServlet addUserServlet = new AddUserServlet();
        addUserServlet.setUserService(userService);

        UpdateUserServlet updateUserServlet = new UpdateUserServlet();
        updateUserServlet.setUserService(userService);

        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
        deleteUserServlet.setUserService(userService);

        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder servletHolderForUsers = new ServletHolder(usersServlet);
        ServletHolder servletHolderForAdd = new ServletHolder(addUserServlet);
        ServletHolder servletHolderForUpdate = new ServletHolder(updateUserServlet);
        ServletHolder servletHolderForDelete = new ServletHolder(deleteUserServlet);
        contextHandler.addServlet(servletHolderForUsers, "/users/*");
        contextHandler.addServlet(servletHolderForAdd, "/users/add");
        contextHandler.addServlet(servletHolderForUpdate, "/users/update/*");
        contextHandler.addServlet(servletHolderForDelete, "/users/delete/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();
    }

}
