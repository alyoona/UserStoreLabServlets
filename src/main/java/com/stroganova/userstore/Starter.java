package com.stroganova.userstore;

import com.stroganova.userstore.dao.UserDao;
import com.stroganova.userstore.dao.jdbc.JdbcUserDao;
import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {

    public static void main(String[] args) throws Exception {
        //dao
        UserDao userDao = new JdbcUserDao();

        //userDao.createUsers();

        //services
        UserService userService = new UserService();
        userService.setUserDao(userDao);

        //servlets
        UsersServlet usersServlet = new UsersServlet();
        usersServlet.setUserService(userService);


        AddUserServlet addUserServlet = new AddUserServlet();
        addUserServlet.setUserService(userService);

        UpdateUserServlet updateUserServlet = new UpdateUserServlet();
        updateUserServlet.setUserService(userService);

        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
        deleteUserServlet.setUserService(userService);

        //server config
        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder servletHolderForUsers = new ServletHolder(usersServlet);
        ServletHolder servletHolderForAdd = new ServletHolder(addUserServlet);
        ServletHolder servletHolderForUpdate = new ServletHolder(updateUserServlet);
        ServletHolder servletHolderForDelete = new ServletHolder(deleteUserServlet);
        contextHandler.addServlet(servletHolderForUsers, "/users/*");
        contextHandler.addServlet(servletHolderForAdd, "/users/add");
        contextHandler.addServlet(servletHolderForUpdate, "/users/update/*");
        contextHandler.addServlet(servletHolderForDelete, "/users/delete/*");
        contextHandler.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");


        //start
        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
        server.join();
    }

}
