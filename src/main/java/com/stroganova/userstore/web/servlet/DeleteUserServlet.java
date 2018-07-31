package com.stroganova.userstore.web.servlet;

import com.stroganova.userstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteUserServlet extends HttpServlet {
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getPathInfo();
        long userId = Long.parseLong(id.substring(1));

        userService.deleteById(userId);


        response.setContentType("text/html");
        //PrintWriter pw = response.getWriter();

        response.sendRedirect("/users");

        //pw.close();
    }
}
