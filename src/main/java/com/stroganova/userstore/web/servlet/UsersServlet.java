package com.stroganova.userstore.web.servlet;

import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.pages.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("users", userService.getAll());

        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("users.html", parameters);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(page);

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
