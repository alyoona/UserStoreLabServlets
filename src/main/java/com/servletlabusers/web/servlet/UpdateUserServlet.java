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

public class UpdateUserServlet extends HttpServlet {

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userId = request.getPathInfo();
      long id = Long.parseLong(userId.substring(1));

        System.out.println(id);

        Map<String,Object> pageVars = new HashMap<>();
        pageVars.put("id", id);

        User user = userService.getById(id);

        pageVars.put("name", user.getName());
        pageVars.put("salary", user.getSalary());

        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVars));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSalary(salary);

        userService.update(user);

        String userInfo = user.userNameAndSalary();

        userInfo = "User has been updated: " + userInfo;
        System.out.println(userInfo);

        Map<String,Object> pageVars = new HashMap<>();
        pageVars.put("id", user.getId());
        pageVars.put("name", user.getName());
        pageVars.put("salary", user.getSalary());
        pageVars.put("userInfo", userInfo);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("update.html", pageVars));
    }

}
