package com.servletlabusers.web.servlet;


import com.servletlabusers.entity.User;
import com.servletlabusers.service.UserService;
import com.servletlabusers.web.templates.PageGenerator;

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

        userService.addUser(user);

        String userInfo = "User has been added: " + user.userNameAndSalary();

        System.out.println(userInfo);

        Map<String,Object> pageVariables = new HashMap<>();
        pageVariables.put("userInfo", userInfo);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("add.html", pageVariables));
    }


}
