package com.stroganova.userstore.web.servlet;


import com.stroganova.userstore.entity.User;
import com.stroganova.userstore.service.UserService;
import com.stroganova.userstore.web.templates.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet{

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(PageGenerator.instance().getPage("add.html", null));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User();
        user.setName(name);
        user.setSalary(salary);

        userService.add(user);

        Map<String,Object> pageVariables = new HashMap<>();
        pageVariables.put("user", user);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("add.html", pageVariables));
    }


}
