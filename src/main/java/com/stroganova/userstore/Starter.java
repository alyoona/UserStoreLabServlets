package com.stroganova.userstore;

import com.stroganova.userstore.config.ApplicationProperties;
import com.stroganova.userstore.dao.jdbc.ConnectionManager;
import com.stroganova.userstore.dao.jdbc.JdbcUserDao;
import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Starter {

    public static void main(String[] args) throws Exception {
        //Properties
        ApplicationProperties applicationProperties = new ApplicationProperties();

        //DB
        ConnectionManager connectionManager = new ConnectionManager(applicationProperties.getProperties());

        //dao
        JdbcUserDao userDao = new JdbcUserDao();
        userDao.setConnectionManager(connectionManager);

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
        contextHandler.addServlet(new ServletHolder(usersServlet), "/users/*");
        contextHandler.addServlet(new ServletHolder(addUserServlet), "/users/add");
        contextHandler.addServlet(new ServletHolder(updateUserServlet), "/users/update/*");
        contextHandler.addServlet(new ServletHolder(deleteUserServlet), "/users/delete/*");
        contextHandler.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");


        //start
        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();

    }

}
